package org.example.pizza_paradise.application.Validation;

import org.example.pizza_paradise.domain.IPizzaRepository;
import org.example.pizza_paradise.domain.IUserRepository;
import org.example.pizza_paradise.domain.Pizza;
import org.example.pizza_paradise.domain.User;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    private final IUserRepository userRepo;
    private final IPizzaRepository pizzaRepo;

    public Validation(IUserRepository userRepo, IPizzaRepository pizzaRepo) {
        this.userRepo = userRepo;
        this.pizzaRepo = pizzaRepo;
    }


    public void validatePizza(Pizza pizza) throws ValidationException {
        if (pizza.getName().isBlank()) {
            throw new ValidationException("Pizza name is required");
        }
        if (pizza.getPrice() <= 0) {
            throw new ValidationException("Pizza price must be greater than 0");
        }
    }

    public boolean validateLogin(String email, String name) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("Email is required");
        }

        if (name == null || name.isBlank()) {
            throw new ValidationException("Name is required");
        }

        User storedUser = userRepo.findByEmail(email);

        if (storedUser == null) {
            throw new ValidationException("User not found");
        }

        return email.equals(storedUser.getEmail()) &&
                name.equals(storedUser.getName());
    }
}