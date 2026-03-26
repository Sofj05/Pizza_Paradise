package org.example.pizza_paradise.domain;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private List<Pizza> pizzas;
    private User user;
    private Date date;
    private double totalPrice;

    public Order(){}

    public Order(int id, List<Pizza> pizzas, User user, double totalPrice) {
        this.id = id;
        this.pizzas = pizzas;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public Order(List<Pizza> pizzas, User user, double totalPrice) {
        this.pizzas = pizzas;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return totalPrice;
    }
    public void setPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

