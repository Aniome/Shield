package org.shield.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class UserController {
    @GetMapping("/")
    public String profile() {
        return "profile";
    }
    @GetMapping("/mine")
    public String mine() {
        return "mine";
    }
}
