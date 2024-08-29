package org.shield.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.shield.entities.UserBlockchain;
import org.shield.repository.UserRepository;
import org.shield.service.Register;
import org.shield.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;

//    @GetMapping("/login")
//    public String login(Authentication authentication, Model model) {
//        System.out.println("get");
//        if (authentication != null){
//            boolean isAdmin = authentication.getAuthorities().stream()
//                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
//            if (isAdmin)
//                return "redirect:/admin";
//            else
//                return "redirect:/profile";
//        }
////        //class="container"
////        //right-panel-active
//        model.addAttribute("container_class", "container");
//        model.addAttribute("user", new UserBlockchain());
//        return "login/login";
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserBlockchain userBlockchain, @RequestHeader("Content-Type") String contentType) {
        System.out.println("post");
        System.out.println(contentType);
        Optional<UserBlockchain> user = userRepository.findByUsername(userBlockchain.getUsername());
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("user log in");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserBlockchain user,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("container_class", "container right-panel-active");
            return "login/login";
        }

        try {
            HttpStatusCode response = Register.register(user);
            if (response == HttpStatus.CREATED){
                return "login/success-registration";
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
