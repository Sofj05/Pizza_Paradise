package org.example.pizza_paradise.application;

import org.example.pizza_paradise.application.Validation.Validation;
import org.example.pizza_paradise.domain.IPizzaRepository;
import org.example.pizza_paradise.domain.Pizza;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {

    private IPizzaRepository pizzaRepo;
    private final Validation validation;

    public PizzaService(IPizzaRepository pizzaRepo,  Validation validation) {
        this.pizzaRepo = pizzaRepo;
        this.validation = validation;
    }

    public void addNewPizza(Pizza pizza){
    }

    public Pizza getPizzaById(int pizzaId){
    return pizzaRepo.findPizzaById(pizzaId);
    }
    public List<Pizza> getPizzaMenu(){
        return pizzaRepo.findAll();
    }

}
