package org.shield.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.shield.entities.UserBlockchain;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

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
    public String register(@ModelAttribute("user") UserBlockchain user) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //converting object in JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);

        //create HTTP-request
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

        String url = "http://localhost:8080/api/new-user";

        //send post request and get response
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        String result = response.getStatusCode().toString();

        return "login/success-registration";
    }

    @GetMapping("/register")
    public String profile() {
        return "login/success-registration";
    }
}
