package com.arep.framework;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class MiniSpark {
    private static final int PORT = 8080;
    private static Path staticFolder = Paths.get("www").toAbsolutePath().normalize();
    private static final Map<String, Route> getRoutes = new HashMap<>();

    private static Object controllerInstance;

    public static void staticfiles(String folder) {
        staticFolder = Paths.get(folder).toAbsolutePath().normalize();
    }

    public static void get(String path, Route route) {
        getRoutes.put(path, route);
    }

    public static void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor en http:localhost:" + PORT);
            System.out.println("Static folder:" + staticFolder);

            while (true) {
                Socket client = serverSocket.accept();
                handle(client);
            }
        }
    }

    private static void handle(Socket socket) throws IOException {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.US_ASCII));

            // 1. Leer la primera línea (método y path)
            String requestLine = in.readLine();
            if (requestLine == null) return;
            String[] parts = requestLine.split(" ");
            if (parts.length < 2) return;
            String method = parts[0];
            URI uri = new URI(parts[1]);

            // 2. Leer headers
            String line;
            int contentLength = 0;
            while (!(line = in.readLine()).isEmpty()) {
                if (line.toLowerCase().startsWith("content-length:")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }
            }

            // 3. Leer body (si hay)
            char[] bodyChars = new char[contentLength];
            if (contentLength > 0) in.read(bodyChars);
            String body = new String(bodyChars);

            // 4. Parsear query params
            Map<String, String> queryParams = new HashMap<>();
            if (uri.getRawQuery() != null) {
                for (String param : uri.getRawQuery().split("&")) {
                    String[] keyVal = param.split("=");
                    if (keyVal.length == 2) {
                        queryParams.put(keyVal[0], keyVal[1]);
                    }
                }
            }

            // 5. Crear Request y Response
            Request req = new Request(method, uri.getPath(), queryParams, body);
            Response res = new Response();

            // 6. Manejo de rutas
            if ("GET".equals(method) && getRoutes.containsKey(uri.getPath())) {
                String result = getRoutes.get(req.getPath()).handle(req, res);
                byte[] data = result.getBytes(StandardCharsets.UTF_8);
                String headers = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: " + res.getType() + "\r\n" +
                        "Content-Length: " + data.length + "\r\n\r\n";
                os.write(headers.getBytes(StandardCharsets.UTF_8));
                os.write(data);
            } else {
                serveStatic(uri.getPath(), os);
            }

            os.flush();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error al manejar la petición");
        }
    }

    private static void serveStatic(String path, OutputStream os) throws IOException {
        if (path.equals("/")) path = "/index.html";
        Path file = staticFolder.resolve("." + path).normalize();

        if (!file.startsWith(staticFolder) || !Files.exists(file)) {
            String notFound = "HTTP/1.1 404 Not Found\r\n\r\nArchivo no encontrado";
            os.write(notFound.getBytes(StandardCharsets.UTF_8));
            return;
        }

        String mime = guessMime(path);
        byte[] bytes = Files.readAllBytes(file);
        String headers = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: " + mime + "\r\n" +
                "Content-Length: " + bytes.length + "\r\n\r\n";
        os.write(headers.getBytes(StandardCharsets.UTF_8));
        os.write(bytes);
    }

    private static String guessMime(String path) {
        if (path.endsWith(".html")) return "text/html";
        if (path.endsWith(".css")) return "text/css";
        if (path.endsWith(".js")) return "application/javascript";
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".gif")) return "image/gif";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".ico")) return "image/x-icon";
        return "text/plain";
    }

    public static void loadComponents(String[] args) {
        try {
            Class<?> controllerClass = Class.forName(args[0]);

            if (controllerClass.isAnnotationPresent(RestController.class)) {
                controllerInstance = controllerClass.getDeclaredConstructor().newInstance();

                for (Method method : controllerClass.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(GetMapping.class)) {
                        GetMapping getMapping = method.getAnnotation(GetMapping.class);
                        String path = getMapping.value();

                        Route service = (request, response) -> {
                            try {
                                Object result;

                                Object[] argsForMethod = new Object[method.getParameterCount()];
                                int i = 0;
                                for (Parameter p : method.getParameters()) {
                                    if (p.isAnnotationPresent(RequestParam.class)) {
                                        String key = p.getAnnotation(RequestParam.class).value();
                                        argsForMethod[i] = request.getQueryParams().getOrDefault(key, "");
                                    }
                                    i++;
                                }
                                result = method.invoke(controllerInstance, argsForMethod);

                                return result.toString();
                            } catch (Exception e) {
                                e.printStackTrace();
                                return "Internal Server Error";
                            }
                        };

                        getRoutes.put(path, service);

                        System.out.println("Ruta cargada: " + path + " -> " + method.getName());
                    }
                }
            } else {
                System.out.println("La clase no tiene la anotación @RestController");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Clase no encontrada: " + args[0], e);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando componentes", e);
        }
    }
}
