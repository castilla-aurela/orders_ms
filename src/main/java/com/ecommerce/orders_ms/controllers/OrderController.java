package com.ecommerce.orders_ms.controllers;

import com.ecommerce.orders_ms.exceptions.OrderNotFoundException;
import com.ecommerce.orders_ms.models.DetailOrder;
import com.ecommerce.orders_ms.models.Order;
import com.ecommerce.orders_ms.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController

public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

        DetailOrder orderDetail1 = new DetailOrder("0001", 12, 50000L, 50000L);

        List<DetailOrder> list= new ArrayList<DetailOrder>();
        list.add(orderDetail1);

        Order order01 = new Order("001", new Date(), 500000, list, "ordered");

        this.orderRepository.save(order01);

    }
    @GetMapping("/orders/{orderId}")
    Order getOrder(@PathVariable String orderId){

        return orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException("No se encontró una orden con el orderId: " + orderId));
    }

    @PostMapping("/newOrder")
    Order newOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }
    @PutMapping("/modOrder/{orderId}")

    Order updateOrder(@PathVariable String orderId, @RequestBody Order order){
        Order modOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("No se encontró una orden con el orderId: " + orderId));
        /*modOrder. */
        return orderRepository.save(order);
    }



}
