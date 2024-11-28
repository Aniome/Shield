package org.shield.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ExceptionShieldHandler {
    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e, Model model) {
        if (e instanceof IllegalArgumentException) {
            return "errors/404";
        }
        if (e instanceof NoResourceFoundException) {
            return "errors/404";
        }
        model.addAttribute("error", "Error - " + e.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleException(AuthenticationException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication Failed: " + exception.getMessage());
    }
}
