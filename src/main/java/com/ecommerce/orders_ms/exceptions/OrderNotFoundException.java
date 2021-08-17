package com.ecommerce.orders_ms.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
}
