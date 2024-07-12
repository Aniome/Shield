package org.shield.controllers;

import org.shield.service.ShieldUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "login";
    }
}
