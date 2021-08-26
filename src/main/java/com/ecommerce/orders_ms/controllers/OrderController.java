package com.ecommerce.orders_ms.controllers;

import com.ecommerce.orders_ms.exceptions.OrderNotFoundException;
import com.ecommerce.orders_ms.models.DetailOrder;
import com.ecommerce.orders_ms.models.Order;
import com.ecommerce.orders_ms.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

        DetailOrder orderDetail1 = new DetailOrder("0001", 12, 50000L, 50000L);

        List<DetailOrder> list= new ArrayList<DetailOrder>();
        list.add(orderDetail1);

        Order order01 = new Order("008", "006", new Date(), 500000, list, "ordered");

        this.orderRepository.save(order01);

    }
    @GetMapping("/orders/{orderId}")
    Order getOrder(@PathVariable String orderId){

        return orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException("No se encontró una orden con el orderId: " + orderId));
    }

    @GetMapping("/ordersbyuserId/{userId}")
    List<Order> getOrderbyId(@PathVariable String userId){

        return orderRepository.findByUserId(userId);
              /*  .orElseThrow(()->new OrderNotFoundException("No se encontró una orden del siguiente userId: " + userId));*/
    }


    @GetMapping("/orders")
    List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    Order newOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @DeleteMapping("/orders/{orderId}")
    public Map<String,String>deleteOrder(@PathVariable String orderId ){
         orderRepository.deleteById(orderId);

        HashMap<String,String> message = new HashMap<>();
        message.put("response","order "+orderId+" has been deleted");
         return message;
    }

    @PutMapping("/orders/{orderId}")
    Order updateOrder(@PathVariable String orderId, @RequestBody Order order){
        Order modOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("No se encontró una orden con el orderId: " + orderId));
        /*modOrder. */

        modOrder.setDate(order.getDate());
        modOrder.setTotal(order.getTotal());
        modOrder.setDetailProducts(order.getDetailProducts());
        modOrder.setStatus(order.getStatus());

        return orderRepository.save(modOrder);
    }

    @PostMapping("/orders/finish/{orderId}")
    Object finishOrder(@PathVariable  String orderId){
        Order modOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("No se encontró una orden con el orderId: " + orderId));
                /*modOrder. */

        HashMap<String,Integer> response = new HashMap<>();
        modOrder.getDetailProducts().forEach((detail)-> {
                    response.put(detail.getIdProduct(), detail.getQuantity());
                }
                );




        modOrder.setStatus("finished");

        return response;


    }





}
