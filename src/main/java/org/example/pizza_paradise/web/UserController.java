package org.example.pizza_paradise.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UserController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/Login")
    public String Login(@RequestParam String name,@RequestParam String email, Model model) {

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        return "Result";
    }


}
