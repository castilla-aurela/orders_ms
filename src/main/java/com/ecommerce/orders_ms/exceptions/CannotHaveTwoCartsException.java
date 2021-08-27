package com.ecommerce.orders_ms.exceptions;

public class CannotHaveTwoCartsException extends RuntimeException{
    public CannotHaveTwoCartsException(String message){
        super(message);
    }
}
