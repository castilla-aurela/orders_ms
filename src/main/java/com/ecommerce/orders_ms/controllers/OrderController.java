package com.ecommerce.orders_ms.controllers;

import com.ecommerce.orders_ms.exceptions.OrderNotFoundException;
import com.ecommerce.orders_ms.models.DetailOrder;
import com.ecommerce.orders_ms.models.Order;
import com.ecommerce.orders_ms.repositories.OrderRepository;

import org.apache.tomcat.InstrumentableClassLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import static java.util.Objects.isNull;

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

    /* Get Order by UserId*/
    @GetMapping("/ordersbyuserId/{userId}")
    List<Order> getOrderbyId(@PathVariable String userId){

        return orderRepository.findByUserId(userId);
              /*  .orElseThrow(()->new OrderNotFoundException("No se encontró una orden del siguiente userId: " + userId));*/
    }

    /* Get Order by Status*/
    /*
    @GetMapping("/ordersbystatus/{status}")
    List<Order> getOrderbyStatus(@PathVariable String status){

        return orderRepository.findByStatus(status);
              /*  .orElseThrow(()->new OrderNotFoundException("No se encontró una orden del siguiente userId: " + userId));
    }
    */

    @GetMapping("/orders")
    List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    /* Create Order*/
    @PostMapping("/orders")
    Order newOrder(@RequestBody Order order){
       //Order  prevOrder = orderRepository.findByStatusAndUserId("In Progress", order.getUserId());
       //System.out.print(prevOrder);
       
      /* if (!isNull(prevOrder)){
        prevOrder.setDate(order.getDate());
        prevOrder.setTotal(order.getTotal());
        prevOrder.setDetailProducts(order.getDetailProducts());
        prevOrder.setStatus(order.getStatus());
        prevOrder.setUserId(order.getUserId());
        prevOrder.setOrderId(order.getOrderId());
        System.out.print("LISTO");
        //return prevOrder;
        return orderRepository.(prevOrder);
       }*/
        return orderRepository.save(order);
        //return 1;
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
        modOrder.setUserId(order.getUserId());
        modOrder.setOrderId(order.getOrderId());

        return orderRepository.save(modOrder);
    }

    @PostMapping("/orders/finish/{orderId}")
    Object finishOrder(@PathVariable  String orderId){
        Order modOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("No se encontró una orden con el orderId: " + orderId));
                /*modOrder. */
       System.out.print("Prueba");
        modOrder.setStatus("finished");
        return orderRepository.save(modOrder);

    }





}
