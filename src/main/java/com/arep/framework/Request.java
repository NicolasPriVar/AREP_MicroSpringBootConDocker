package com.arep.framework;

import java.net.URI;
import java.util.*;

public class Request {
    private final String method;
    private final String path;
    private final String queryParams;
    private final String body;

    public Request(String method, String path, String body) {
        this.method = method;
        this.path = path;
        this.body = body;
    }

    public Request(String method, String path, String queryParams, String body) {
        this.method = method;
        this.path = path;
        this.queryParams = queryParams;
        this.body = body;
    }

    public String getMethod() { return method; }
    public String getPath() { return path; }
    public String getBody() { return body; }

    public String getQueryParams() {
        return queryParams;
    }
}
