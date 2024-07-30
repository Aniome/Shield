package org.shield.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.shield.entities.UserBlockchain;
import org.shield.service.Register;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String login(Authentication authentication, Model model) {
        if (authentication != null){
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            if (isAdmin)
                return "redirect:/admin";
            else
                return "redirect:/profile";
        }
        //class="container"
        //right-panel-active
        model.addAttribute("container_class", "container");
        model.addAttribute("user", new UserBlockchain());
        return "login/login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserBlockchain user, Model model,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "login/login";

        //model.addAttribute("container_class", "container right-panel-active");
        try {
            HttpStatusCode response = Register.register(user);
            if (response == HttpStatus.CREATED){
                return "login/success-registration";
            } else if (response == HttpStatus.BAD_REQUEST) {
                String error = "Такой пользователь уже существует " + response;
                model.addAttribute("error", error);
            } else {
                String error = "Во время регистрации произошла ошибка " + response;
                model.addAttribute("error", error);
                return "login/failure-registration";
            }
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
