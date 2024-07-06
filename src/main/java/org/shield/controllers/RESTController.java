package org.shield.controllers;

import lombok.AllArgsConstructor;
import org.shield.entities.Block;
import org.shield.entities.UserBlockchain;
import org.shield.service.Impl.BlockServiceImpl;
import org.shield.service.Impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RESTController {
    BlockServiceImpl blockService;
    UserServiceImpl userService;

    @GetMapping("/mine")
    public void mine() {

    }

    @GetMapping("/chain")
    public List<Block> getChain() {
        return blockService.getBlocks();
    }

    @PostMapping("/new-user")
    public String newUser(@RequestBody UserBlockchain user) {
        userService.saveUser(user);
        return "user saved";
    }
}
