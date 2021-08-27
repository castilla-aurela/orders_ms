package com.ecommerce.orders_ms.repositories;

import java.util.List;

import com.ecommerce.orders_ms.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

    List <Order> findByUserId(String userId); 
    List <Order> findByStatus(String status); 
}
