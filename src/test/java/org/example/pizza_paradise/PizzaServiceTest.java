package org.example.pizza_paradise;

import org.example.pizza_paradise.application.PizzaService;
import org.example.pizza_paradise.domain.IPizzaRepository;
import org.example.pizza_paradise.domain.Pizza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PizzaServiceTest {

    private PizzaService pizzaService;
    private IPizzaRepository pizzaRepo = new IPizzaRepository(){};

    @BeforeEach
    void setUp(){
        pizzaService = new PizzaService(pizzaRepo);
    }

    @Test
    void testShowPizzaList(){
        List<Pizza> pizzaList = pizzaService.showPizzaList();

        assertNotNull(pizzaList);
        assertEquals(2,pizzaList.size());
    }

    @Test
    void testMakeNewPizzaObject(){
        Pizza pizza = new Pizza();
        pizzaService.makeNewPizza(pizza);

    }

}
