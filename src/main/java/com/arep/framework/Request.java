package com.arep.framework;

import java.net.URI;
import java.util.*;

public class Request {
    private final String method;
    private final String path;
    private final Map<String, String> queryParams;
    private final String body;

    public Request(String method, String path, Map<String, String> queryParams, String body) {
        this.method = method;
        this.path = path;
        this.queryParams = queryParams;
        this.body = body;
    }

    public String getMethod() { return method; }
    public String getPath() { return path; }
    public String getBody() { return body; }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }
}
