package org.shield.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shield.entities.UserBlockchain;
import org.shield.service.Register;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String login(Authentication authentication) {
        if (authentication != null){
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            if (isAdmin)
                return "redirect:/admin";
            else
                return "redirect:/profile";
        }
        return "login/login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserBlockchain user, Model model) {
        try {
            HttpStatusCode response = Register.register(user);
            if (response == HttpStatus.OK){
                return "login/success-registration";
            } else {
                String error = "Во время регистрации произошла ошибка " + response;
                model.addAttribute("error", error);
                return "login/failure-registration";
            }
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "login/success-registration";
    }

    @GetMapping("/register")
    public String profile() {
        return "login/success-registration";
    }
}
