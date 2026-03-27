package org.example.pizza_paradise.domain;

import java.util.List;

public interface IPizzaRepository {

    void save(Pizza pizza);

    List<Pizza> findAll();

    Pizza findPizzaById(int pizzaId);
}
