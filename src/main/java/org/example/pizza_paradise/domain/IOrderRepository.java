package org.example.pizza_paradise.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IOrderRepository {
    int insertOrder(Order order);
    void save(Order order);
    List<Order> findOrdersByUserEmail(String email);
    void insertOrderPizzas(int orderId, List<Pizza> pizzas);

}
