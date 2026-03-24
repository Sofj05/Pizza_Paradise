package org.example.pizza_paradise.domain;

import org.example.pizza_paradise.domain.Enum.Toppings;

import java.util.List;


public class Pizza {
    private String name;
    private int price;
    private List<Toppings> toppingsList;
    private String description;

    public Pizza(){}

    public Pizza(String name, int price, List<Toppings> toppingsList, String description){

        this.name = name;
        this.price=price;
        this.toppingsList = toppingsList;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
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
}
