package org.example.pizza_paradise.domain.Enum;

public enum Toppings {
    CHEESE("Cheese", 5.0),
    BACON("Bacon", 15.0),
    CHILI("Chili", 15.0),
    DRESSING("Dressing", 10.0),
    PEPPERONI("Pepperoni", 8.0),
    PINEAPPLE("Pineapple", 15.0),
    HAM("Ham", 8.0);

    private final String name;
    private final double price;

    Toppings(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }

}
