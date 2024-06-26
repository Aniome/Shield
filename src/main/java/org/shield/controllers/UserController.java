package org.shield.controllers;

import lombok.AllArgsConstructor;
import org.shield.entities.Block;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/profile")
public class UserController {
    RESTController restController;

    @GetMapping("/")
    public String profile() {
        return "profile";
    }

    @ResponseBody
    @GetMapping("/chain")
    public List<Block> showBlockChain() {
        return restController.hello();
    }

    @GetMapping("/mine")
    public String mine() {
        return "mine";
    }
}
