package com.arep.framework;

import com.arep.framework.GetMapping;
import com.arep.framework.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Saludos desde MiniSpark!";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) {
        return "Hello " + name;
    }

    @GetMapping("/pi")
    public String pi() {
        return String.valueOf(Math.PI);
    }
}
