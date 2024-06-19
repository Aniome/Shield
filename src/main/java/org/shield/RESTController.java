package org.shield;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
