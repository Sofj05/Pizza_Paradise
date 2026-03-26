package org.example.pizza_paradise.application.Validation;

import org.example.pizza_paradise.domain.Pizza;

public class Validation {

    public void validatePizza(Pizza pizza) throws IllegalArgumentException{
        if (pizza.getName().isBlank()){
            throw new IllegalArgumentException("Pizza name is required");
        }
        if (pizza.getPrice() <= 0){
            throw new IllegalArgumentException("Pizza price is required");
        }
    }
}
