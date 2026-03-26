package org.example.pizza_paradise.application.Validation;

import org.example.pizza_paradise.domain.Pizza;
import org.example.pizza_paradise.domain.User;

public class Validation {

    public void validatePizza(Pizza pizza) throws IllegalArgumentException{
        if (pizza.getName().isBlank()){
            throw new IllegalArgumentException("Pizza name is required");
        }
        if (pizza.getPrice() <= 0){
            throw new IllegalArgumentException("Pizza price is required");
        }
    }

    public void validateLogin(User user){
        if (user.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getName().isBlank()){
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getEmail())

    }
}
