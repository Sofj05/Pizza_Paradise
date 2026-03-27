package org.example.pizza_paradise.web;

import org.example.pizza_paradise.application.UserService;
import org.example.pizza_paradise.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UserController {

    private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String HandleLogin(@RequestParam String name,@RequestParam String email, Model model) {

        try{
            User user = userService.login(email,name);

            model.addAttribute("user",user);
            return "result";
        } catch(IllegalArgumentException | EmptyResultDataAccessException e){
            model.addAttribute("message",e.getMessage());
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String HandleRegister(@ModelAttribute("user") User user){
        userService.createUser(user);

        return "redirect:/login";
    }



}
