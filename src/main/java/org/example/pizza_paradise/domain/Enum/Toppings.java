package org.example.pizza_paradise.domain.Enum;

public enum Toppings {
    CHEESE("Cheese", 5.0),
    BACON("Bacon", 15.0),
    CHILI("Chili", 15.0),
    DRESSING("Dressing", 10.0),
    PEPPERONI("Pepperoni", 10.0),
    PINEAPPLE("Pineapple", 15.0);

    private final String description;
    private final double price;

    Toppings(String description, double price){
        this.description = description;
        this.price = price;
    }

    public String getDescription(){
        return description;
    }
    public double getPrice(){
        return price;
    }

}
