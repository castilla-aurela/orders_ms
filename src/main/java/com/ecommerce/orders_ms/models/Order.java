package com.ecommerce.orders_ms.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Order {
    @Id
    private String orderId;
    private Date date;
    private Integer total;
    private String id_detailProducts;
    private String status;

    public Order(String orderId, Date date, Integer total, String id_detailProducts, String status) {
        this.orderId = orderId;
        this.date = date;
        this.total = total;
        this.id_detailProducts = id_detailProducts;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getId_detailProducts() {
        return id_detailProducts;
    }

    public void setId_detailProducts(String id_detailProducts) {
        this.id_detailProducts = id_detailProducts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
