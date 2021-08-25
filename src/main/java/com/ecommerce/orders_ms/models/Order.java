package com.ecommerce.orders_ms.models;

import org.springframework.data.annotation.Id;
import com.ecommerce.orders_ms.models.DetailOrder;

import java.util.Date;
import java.util.List;

public class Order {
    @Id
    private String userId;
    private String orderId;
    private Date date;
    private Integer total;
    private List<DetailOrder> detailProducts;
    private String status;

    public Order(String userId, String orderId, Date date, Integer total, List<DetailOrder> detailProducts, String status) {
        this.userId = userId;
        this.orderId = orderId;
        this.date = date;
        this.total = total;
        this.detailProducts = detailProducts;
        this.status = "In Progress";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public List<DetailOrder> getDetailProducts() {
        return detailProducts;
    }

    public void setDetailProducts(List<DetailOrder> detailProducts) {
        this.detailProducts = detailProducts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
