package org.shield.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.shield.entities.Block;
import org.shield.service.Impl.BlockServiceImpl;
import org.shield.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/profile")
public class UserController {
    RESTController restController;
    BlockServiceImpl blockServiceImpl;
    UserServiceImpl userServiceImpl;

    @GetMapping()
    public String profile() {
        //userServiceImpl.testDB();
        return "profile";
    }

    @ResponseBody
    @GetMapping("/chain")
    public List<Block> showBlockChain() {
        return restController.getChain();
    }

    @GetMapping("/create")
    public String mine(Model model) {
        model.addAttribute("block", new Block());
        return "create";
    }

    @PostMapping("/create")
    public String Create(@ModelAttribute("block") @Valid Block block,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "create";
        blockServiceImpl.addBlock(block);
        return "redirect:/profile";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        model.addAttribute("password", "");
        return "change-password";
    }

    @PatchMapping("/change-password")
    public String changePassword(@ModelAttribute("password") String password, Principal principal) {
        if (userServiceImpl.updatePassword(principal.getName(), password)){
            System.out.println("password updated");
            return "redirect:/profile";
        }
        return "redirect:/profile/chain";
    }
}
