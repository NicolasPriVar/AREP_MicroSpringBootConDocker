---  
# Docker  
---  

Usando el comando docker build --tag microcondocker . creamos la imagen con el nombre microcondocker, y la vemos al observar las imagenes que hemos creado.  

<img width="632" height="46" alt="{568E6E19-152C-4DEF-B9D9-41030320969B}" src="https://github.com/user-attachments/assets/1197af57-6ecb-48b4-abd1-93d9d2fdb100" /> 
Y también la podemos ver en la aplicación de Docker Escritorio.  
<img width="933" height="82" alt="{D6299FC7-9184-450E-956F-AD1645A644C7}" src="https://github.com/user-attachments/assets/50766093-da6a-4a12-a054-7368132cb709" />  
Y nos aseguramos que está corriendo con el comando "docker ps".  
<img width="967" height="59" alt="{ED67616B-33CD-4B7C-917F-4E6134211816}" src="https://github.com/user-attachments/assets/372d8516-f9be-46a5-a075-e9491a0cc00d" />  
Y creamos las 3 instancias.  
<img width="1053" height="111" alt="{93F5063B-EAA2-4601-8809-3364B2FC3490}" src="https://github.com/user-attachments/assets/0f410af9-a930-4873-aabb-ad9b667b39f0" />  
Las tenemos también corriendo.  
<img width="961" height="218" alt="{CCCF0B0A-A932-4927-B446-D09F6696B240}" src="https://github.com/user-attachments/assets/154dafd8-03b4-4ca8-a08a-fa399d073149" />  
Y podemos verificar que funcionan al momento de abrir desde el navegador "http://localhost:34000" o "http://localhost:34001" o "http://localhost:34002"  
<img width="237" height="70" alt="{B79AD7CC-8F35-460F-83C6-E3E744736A44}" src="https://github.com/user-attachments/assets/bf6aa371-d408-4a25-beb6-6b20a6abdb82" /> 
Creamos el docker-compose para tener un container y una instancia a de mongo en otro container y verificamos que está corriendo.  
<img width="2244" height="341" alt="{3668CCB6-3D05-4614-9850-03F32F90FD56}" src="https://github.com/user-attachments/assets/38b30a4a-eb95-4b58-b0e3-948745673f9c" /> 
Lo subimos a dockerhub y verificamos que se conectó, usando el comando "docker images" obtenemos.  
<img width="765" height="26" alt="{6F33D8DB-3593-4F94-9A79-882EE155FC28}" src="https://github.com/user-attachments/assets/fce853b1-c3b4-47d6-9009-6446b5395422" /> 
Hacemos el push al repositorio de Docker y queda listo.  
<img width="918" height="506" alt="{B59E412D-0902-4500-A68A-29BD50BE943C}" src="https://github.com/user-attachments/assets/fb6df919-7e62-4fee-9e6d-c99eb2cff07d" />  

---  
# AWS  
---  
Ahora creamos la instancia EC2 en AWS.  
<img width="1658" height="645" alt="{7BF8A7D1-3E4E-421C-BDE6-76EC9894D04B}" src="https://github.com/user-attachments/assets/9b1ce114-b36f-4f6f-b1bb-6a8e1da92960" />  
Luego de configurar todo, bajamos el repositorio que creamos en DockerHub.  
<img width="831" height="132" alt="{0CB194F7-D159-4D3C-9A75-158EBC3F7101}" src="https://github.com/user-attachments/assets/5d9d5ac6-0448-419f-8bb9-0062c6243738" />  
Y finalmente hacemos las pruebas con la IP pública de la instancia.  
<img width="332" height="67" alt="{4D52E871-547F-4B91-B3F8-BCF8102260ED}" src="https://github.com/user-attachments/assets/5d03c3e3-2c07-40bf-96e6-c5e9ec9763ef" />  
Con pi.  
<img width="261" height="72" alt="{3B94F9D5-33FB-437A-959A-9AF43D89287D}" src="https://github.com/user-attachments/assets/04321617-3001-43e4-98a2-e811530b3220" />


