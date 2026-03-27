package org.example.pizza_paradise.application;

import org.example.pizza_paradise.application.Validation.Validation;
import org.example.pizza_paradise.domain.IOrderRepository;
import org.example.pizza_paradise.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private IOrderRepository orderRepo;
    private final Validation validation;

    public OrderService(IOrderRepository orderRepo, Validation validation) {
        this.orderRepo = orderRepo;
        this.validation = validation;
    }

    public void createOrder(Order order) {
        orderRepo.save(order);
    }

}
