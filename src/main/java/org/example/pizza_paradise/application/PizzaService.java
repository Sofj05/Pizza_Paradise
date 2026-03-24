package org.example.pizza_paradise.application;

import org.example.pizza_paradise.domain.IPizzaRepository;
import org.example.pizza_paradise.domain.Pizza;

import java.util.List;

public class PizzaService {

    private IPizzaRepository pizzaRepo;

    public PizzaService(IPizzaRepository pizzaRepo){
        this.pizzaRepo = pizzaRepo;
    }

    public void makeNewPizza(Pizza pizza){

    }

    public List<Pizza> showPizzaList(){

        return List.of();
    }

}
