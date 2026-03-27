package org.example.pizza_paradise.application.Validation;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ValidationException.class)
//    public String handleValidationException(ValidationException e, Model model){
//        model.addAttribute("errMessage", e.getMessage());
//        return "error";
//    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model){
        model.addAttribute("errMessage", e.getMessage());
        return "error";
    }

}
