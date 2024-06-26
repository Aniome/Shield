package org.shield.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    @GetMapping("/hello-world")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/helloAll")
    public String helloAll() {
        return "Hello World everybody";
    }
}
