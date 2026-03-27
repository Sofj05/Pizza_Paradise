package org.example.pizza_paradise.web;

import org.example.pizza_paradise.application.OrderService;
import org.example.pizza_paradise.application.PizzaService;
import org.example.pizza_paradise.application.UserService;
import org.example.pizza_paradise.application.Validation.ValidationException;
import org.example.pizza_paradise.domain.Order;
import org.example.pizza_paradise.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UserController {

    private final UserService userService;
    private final PizzaService pizzaService;
    private final OrderService orderService;

public UserController(UserService userService,  PizzaService pizzaService, OrderService orderService) {
    this.userService = userService;
    this.pizzaService = pizzaService;
    this.orderService = orderService;
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
        } catch(ValidationException e){
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

    @GetMapping("/pizzaMenu")
    public String pizzaMenu(Model model){
        model.addAttribute("pizzas", pizzaService.getPizzaMenu());
        return "pizzaMenu";
    }

    @GetMapping("/createOrder")
    public String createOrder(Model model){
        model.addAttribute("pizzaList", pizzaService.getPizzaMenu());
        orderService.createOrder(order);
        return "createOrder";
    }

    @PostMapping("/addToOrder")
    public String addToOrder(@ModelAttribute("order") Order order, Model model){
        model.addAttribute("pizzaList", pizzaService.getPizzaMenu());
        return "pizzaMenu";
    }



}
