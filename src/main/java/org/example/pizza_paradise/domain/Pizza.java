package org.example.pizza_paradise.domain;

import org.example.pizza_paradise.domain.Enum.Toppings;

import java.util.List;


public class Pizza {
    private int id;
    private String name;
    private double price;
    private List<Toppings> toppingsList;
    private String description;
    private int quantity;

    public Pizza(){}

    public Pizza(String name, double price, List<Toppings> toppingsList, String description){
        this.name = name;
        this.price=price;
        this.toppingsList = toppingsList;
        this.description = description;
        this.quantity = 1;
    }

    public Pizza(int id, String name, double price, List<Toppings> toppingsList, String description){
        this.id = id;
        this.name = name;
        this.price=price;
        this.toppingsList = toppingsList;
        this.description = description;
        this.quantity = 1;
    }

    public Pizza(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = 1;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public List<Toppings> getToppingsList() {
        return toppingsList;
    }
    public void setToppingsList(List<Toppings> toppingsList) {
        this.toppingsList = toppingsList;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
