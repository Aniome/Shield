package org.shield.controllers;

import lombok.AllArgsConstructor;
import org.shield.entities.Block;
import org.shield.service.Impl.BlockServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RESTController {
    BlockServiceImpl blockService;

    @GetMapping("/hello-world")
    public List<Block> hello() {
        return blockService.getBlocks();
    }

    @GetMapping("/helloAll")
    public String helloAll() {
        return "Hello World everybody";
    }
}
