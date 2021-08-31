package com.ecommerce.orders_ms.controllers;

import com.ecommerce.orders_ms.exceptions.CannotHaveTwoCarts;
import com.ecommerce.orders_ms.exceptions.CannotHaveTwoCartsException;
import com.ecommerce.orders_ms.exceptions.OrderNotFoundException;
import com.ecommerce.orders_ms.models.DetailOrder;
import com.ecommerce.orders_ms.models.Order;
import com.ecommerce.orders_ms.repositories.OrderRepository;

import org.apache.tomcat.InstrumentableClassLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import static java.util.Objects.isNull;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController

public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;



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

    /* Get Order by Status and Id*/

    @GetMapping("/orders/{status}/{userId}")
    List<Order> getOrderbyStatus(@PathVariable String status,@PathVariable String userId) {

        return orderRepository.findByStatusAndUserId(status, userId);
    }

    @GetMapping("/orders")
    List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    /* Create Order*/
    @PostMapping("/orders")
    Order newOrder(@RequestBody Order order){
       List<Order>  prevOrder = orderRepository.findByStatusAndUserId("In Progress", order.getUserId());

       if(prevOrder.size() >0){
           Order ordupdt = prevOrder.get(0);
           return  this.updateOrder(ordupdt.getOrderId(),ordupdt);
       }
       order.setStatus("In Progress"); 
       return orderRepository.save(order);

    }

    @PostMapping("/addCart/{userId}")
    Order addCart(@RequestBody DetailOrder detailOrder, @PathVariable String userId){

        List<Order>  prevOrder = orderRepository.findByStatusAndUserId("In Progress", userId);
        boolean exist = true;
        if(prevOrder.size() >0){
            Order ordupdt = prevOrder.get(0);

            for (int i = 0; i < ordupdt.getDetailProducts().size(); i++) {
                if(ordupdt.getDetailProducts().get(i).getIdProduct().equals(detailOrder.getIdProduct())){
                    ordupdt.getDetailProducts().get(i).setQuantity(ordupdt.getDetailProducts().get(i).getQuantity()+detailOrder.getQuantity());
                    Long prevSubtotal = ordupdt.getDetailProducts().get(i).getSubTotal();
                    ordupdt.getDetailProducts().get(i).setSubTotal(ordupdt.getDetailProducts().get(i).getSubTotal()+detailOrder.getSubTotal());
                    if(ordupdt.getDetailProducts().size()==1){
                        ordupdt.setTotal(ordupdt.getDetailProducts().get(i).getSubTotal());
                    }
                    else if(ordupdt.getDetailProducts().size()>1){
                        ordupdt.setTotal(ordupdt.getTotal()+ordupdt.getDetailProducts().get(i).getSubTotal()-prevSubtotal);
                    }

                    return  this.updateOrder(ordupdt.getOrderId(),ordupdt);
                }
            }
            List<DetailOrder> newDetail = ordupdt.getDetailProducts();
            newDetail.add(detailOrder);
            ordupdt.setDetailProducts(newDetail);
            ordupdt.setTotal(ordupdt.getTotal()+detailOrder.getSubTotal());
            return  this.updateOrder(ordupdt.getOrderId(),ordupdt);
        }
     List<DetailOrder> newDetail = new ArrayList<>();
        newDetail.add(detailOrder);
    Order createOrder = new Order(null,userId,new Date(), detailOrder.getSubTotal(),newDetail,"In Progress",null);
        return orderRepository.save(createOrder);
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

        Date date = new Date();
        modOrder.setFinishDate(date);
        modOrder.setStatus("finished");
        return orderRepository.save(modOrder);

    }





}
