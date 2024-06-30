package org.shield.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.shield.model.Block;
import org.shield.service.Impl.BlockServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/profile")
public class UserController {
    RESTController restController;
    BlockServiceImpl blockServiceImpl;

    @GetMapping()
    public String profile() {
        return "profile";
    }

    @ResponseBody
    @GetMapping("/chain")
    public List<Block> showBlockChain() {
        return restController.hello();
    }

    @GetMapping("/mine")
    public String mine(Model model) {
        model.addAttribute("block", new Block());
        return "mine";
    }

    @PostMapping()
    public String Create(@ModelAttribute("block") @Valid Block block,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "mine";
        blockServiceImpl.addBlock(block);
        return "redirect:/profile";
    }
}
