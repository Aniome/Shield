package org.shield.exception;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShieldErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "errors/403";
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errors/500";
            } else if (statusCode == HttpStatus.REQUEST_TIMEOUT.value()) {
                return "errors/408";
            } else {
                model.addAttribute("error", "Error - " + statusCode);
                return "errors/error";
            }
        }
        model.addAttribute("error", "Unknown Error");
        return "errors/error";
    }
}
