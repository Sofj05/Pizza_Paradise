package org.example.pizza_paradise.domain;

public interface IOrderRepository {
    int insertOrder(Order order);
    void save(Order order);
}
