package com.ecommerce.orders_ms.models;

import org.springframework.data.annotation.Id;
import com.ecommerce.orders_ms.models.DetailOrder;

import java.util.Date;
import java.util.List;

public class Order {
    @Id
    
    private String orderId;
    private String userId;
    private Date date;
    private Long total;
    private List<DetailOrder> detailProducts;
    private String status;
    private Date finishDate;

    public Order(String orderId, String userId, Date date, Long total, List<DetailOrder> detailProducts, String status,Date finishDate) {
        
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.total = total;
        this.detailProducts = detailProducts;
        this.status = status;
        this.finishDate = finishDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finisHDate) {
        this.finishDate = finisHDate;
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
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
