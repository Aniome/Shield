package org.shield.controllers;

import lombok.AllArgsConstructor;
import org.shield.entities.Block;
import org.shield.service.Impl.BlockServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/profile")
public class UserController {
    RESTController restController;
    BlockServiceImpl blockServiceImpl;

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

    @PostMapping()
    public String Create(@ModelAttribute("block") Block block,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/new";
        blockServiceImpl.addBlock(block);
        return "redirect:/profile/";
    }
}
