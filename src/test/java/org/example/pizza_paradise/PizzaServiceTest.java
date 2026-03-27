package org.example.pizza_paradise;

import org.example.pizza_paradise.application.PizzaService;
import org.example.pizza_paradise.application.Validation.Validation;
import org.example.pizza_paradise.domain.Enum.Toppings;
import org.example.pizza_paradise.domain.IPizzaRepository;
import org.example.pizza_paradise.domain.IUserRepository;
import org.example.pizza_paradise.domain.Pizza;
import org.example.pizza_paradise.infrastructure.JdbcPizzaRepository;
import org.example.pizza_paradise.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PizzaServiceTest {

    private PizzaService pizzaService;
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private IPizzaRepository pizzaRepo = new JdbcPizzaRepository(jdbcTemplate);
    private IUserRepository userRepo = new JdbcUserRepository(jdbcTemplate);
    private Validation validation = new Validation(userRepo, pizzaRepo);
    private List<Toppings> toppings;

    @BeforeEach
    void setUp(){
        pizzaService = new PizzaService(pizzaRepo, validation);
        toppings =  new ArrayList<>();
    }
    //Tester at Pizzaen bliver lavet og at den indeholder det nødvendige data
    @Test
    void addPizza_shouldStoreInList(){
        pizzaService.addNewPizza(new Pizza("Hawaii", 80, toppings,""));
        pizzaService.addNewPizza( new Pizza("Vesuvio", 75, toppings,""));
        List<Pizza> pizzaList = pizzaService.getPizzaMenu();
        assertNotNull(pizzaList);
        assertEquals(2,pizzaList.size());
        assertEquals("Hawaii",pizzaList.get(0).getName());
        assertEquals("Vesuvio",pizzaList.get(1).getName());
    }
    @Test
    void newPizza_ShouldHavePrice(){
        Pizza p = new Pizza("Hawaii",80,toppings,"");
        assertEquals(80,p.getPrice());
    }
    @Test
    void newPizza_ShouldHaveToppings(){
        toppings.add(Toppings.PINEAPPLE);
        toppings.add(Toppings.PEPPERONI);
        Pizza p = new Pizza("",0,toppings,"");
        assertEquals(2,p.getToppingsList().size());
        System.out.println(p.getToppingsList().toString());
    }
    // tester exceptions ved indtastning af data til ny pizza
    @Test
    void newPizza_shouldRejectBlankName(){
        assertThrows(IllegalArgumentException.class,
                () -> pizzaService.addNewPizza(new Pizza("  ",50,toppings,"")));
    }
    @Test
    void newPizza_shouldRejectNonPositivePrice(){
        assertThrows(IllegalArgumentException.class,
                () -> pizzaService.addNewPizza(new Pizza("pizzaT", 0, toppings,"")));

        assertThrows(IllegalArgumentException.class,
                () -> pizzaService.addNewPizza(new Pizza("pizzaT", -50, toppings,"")));
    }
    @Test
    void newPizza_shouldRejectEmptyToppingsList(){
        assertThrows(IllegalArgumentException.class,
                ()-> pizzaService.addNewPizza(new Pizza("Test",0,toppings,"")));
    }

    //BrugerTests
    @Test
    void newUser_shouldRejectBlankName(){

    }
    @Test
    void newUser_shouldStartWithZeroBonusPoints(){

    }
    @Test
    void newUser_shouldRejectNonPositivePrice(){

    }

}
