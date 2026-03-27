package org.example.pizza_paradise.web;

import jakarta.servlet.http.HttpSession;
import org.example.pizza_paradise.application.OrderService;
import org.example.pizza_paradise.application.PizzaService;
import org.example.pizza_paradise.application.UserService;
import org.example.pizza_paradise.application.Validation.ValidationException;
import org.example.pizza_paradise.domain.Order;
import org.example.pizza_paradise.domain.Pizza;
import org.example.pizza_paradise.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

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
    public String HandleLogin(@RequestParam String name,@RequestParam String email, HttpSession session, Model model) {
        try{
            User user = userService.login(email,name);
            session.setAttribute("user", user);
            model.addAttribute("user",user);

            List<Order> userOrder = orderService.getOrders(user.getEmail());
            model.addAttribute("allOrders",userOrder);
            return "result";
        } catch(ValidationException e){
            model.addAttribute("message",e.getMessage());
            return "login";
        }
    }
    @GetMapping("/result")
    public String result(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("allOrders",orderService.getOrders(user.getEmail()));
        return "result";
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
    public String createOrder(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            order = new Order();
            order.setUser(user);
            order.setDate(new Date(System.currentTimeMillis()));
            session.setAttribute("order", order);
        }

        model.addAttribute("pizzaList", pizzaService.getPizzaMenu());
        model.addAttribute("order", order);

        double total = order.getTotalPrice();
        model.addAttribute("total", total);

        return "createOrder";
    }

    @PostMapping("/addToOrder")
    public String addToOrder(@RequestParam int pizzaId, HttpSession session) {

        Order order = (Order) session.getAttribute("order");
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        order.addPizza(pizza);

        return "redirect:/createOrder";
    }
    @PostMapping("/confirmOrder")
    public String confirmOrder(HttpSession session, Model model) {

        Order order = (Order) session.getAttribute("order");
        orderService.createOrder(order);
        model.addAttribute("order", order);
        session.removeAttribute("order");
        return "orderConfirmation";
    }
}
