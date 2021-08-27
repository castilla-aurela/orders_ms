package com.ecommerce.orders_ms.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class CannotHaveTwoCarts {


    @ResponseBody
    @ExceptionHandler(CannotHaveTwoCartsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotFoundAdvice(CannotHaveTwoCartsException exception){
        return exception.getMessage();
    }
}
