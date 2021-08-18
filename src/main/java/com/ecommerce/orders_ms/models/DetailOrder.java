package com.ecommerce.orders_ms.models;

public class DetailOrder {
    private String idProduct;
    private Integer quantity;
    private Long price;
    private Long subTotal;

    public DetailOrder(String idProduct, Integer quantity, Long price, Long subTotal) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Long subTotal) {
        this.subTotal = subTotal;
    }
}
