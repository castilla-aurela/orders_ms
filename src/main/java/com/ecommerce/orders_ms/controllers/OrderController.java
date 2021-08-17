package com.ecommerce.orders_ms.controllers;

import com.ecommerce.orders_ms.exceptions.OrderNotFoundException;
import com.ecommerce.orders_ms.models.Order;
import com.ecommerce.orders_ms.repositories.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

        Order order01 = new Order("001", new Date(), 500000, "005", "ordered");

        this.orderRepository.save(order01);
    }
    @GetMapping("/orders/{orderId}")
    Order getOrder(@PathVariable String orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException("No se encontro una order con el orderId: " + orderId));
    }

}
