package com.ecommerce.orders_ms.repositories;

import com.ecommerce.orders_ms.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
