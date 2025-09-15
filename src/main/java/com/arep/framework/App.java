package com.arep.framework;

import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            args = new String[]{"com.arep.framework.HelloController"};
        }
        MiniSpark.loadComponents(args);
        MiniSpark.start();
    }
}