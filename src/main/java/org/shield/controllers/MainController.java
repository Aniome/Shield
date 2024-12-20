package org.shield.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.shield.dto.JwtAuthenticationDto;
import org.shield.dto.RefreshTokenDto;
import org.shield.dto.UserCredentialsDto;
import org.shield.entities.UserBlockchain;
import org.shield.service.Impl.UserServiceImpl;
import org.shield.service.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;

import javax.naming.AuthenticationException;

@Controller
public class MainController {

    private UserServiceImpl userService;

    @GetMapping("/")
    public String login(Authentication authentication, Model model) {
        if (authentication != null){
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority()
                            .equals("ROLE_ADMIN"));
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

    @CrossOrigin
    @PostMapping("/login")
    public String singIn(UserCredentialsDto userCredentialsDto, Model model) {
        try {
            JwtAuthenticationDto jwtAuthenticationDto = userService.singIn(userCredentialsDto);
            model.addAttribute("jwtToken", jwtAuthenticationDto.getToken());
            model.addAttribute("jwtRefreshToken", jwtAuthenticationDto.getRefreshToken());
            //return ResponseEntity.ok(jwtAuthenticationDto);
            return "redirect:/";
        } catch (AuthenticationException e) {
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            return "redirect:/";
        }
    }

    @PostMapping("/refresh")
    public JwtAuthenticationDto refresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
        return userService.refreshToken(refreshTokenDto);
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserBlockchain user,
                           BindingResult bindingResult, Model model) {
        if (userService.checkUsername(user.getUsername())) {
            FieldError fieldError = new FieldError("user", "password",
                    "Пользователь с таким именем уже существует");
            bindingResult.addError(fieldError);
        }
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
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
