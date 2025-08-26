package com.arep.framework;

import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws Exception {
        MiniSpark.loadComponents(args);
        MiniSpark.start();
    }
}
