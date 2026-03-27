package org.example.pizza_paradise.application.Validation;

import org.example.pizza_paradise.domain.IUserRepository;
import org.example.pizza_paradise.domain.Pizza;
import org.example.pizza_paradise.domain.User;
import org.example.pizza_paradise.infrastructure.JdbcUserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class Validation {
    JdbcTemplate jdbcTemp = new JdbcTemplate();
    IUserRepository userRepo = new JdbcUserRepository(jdbcTemp);

    public Validation() {

    }

    public void validatePizza(Pizza pizza) throws IllegalArgumentException{
        if (pizza.getName().isBlank()){
            throw new IllegalArgumentException("Pizza name is required");
        }
        if (pizza.getPrice() <= 0){
            throw new IllegalArgumentException("Pizza price is required");
        }
    }

    public boolean validateLogin(String email,String name){
        boolean isValid = false;
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Email is required");
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name is required");
        }

        User storedUser = userRepo.findByEmail(email);

        if (storedUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (email.equals(storedUser.getEmail()) && name.equals(storedUser.getName())){
            isValid = true;
        }
        return isValid;
    }
}
