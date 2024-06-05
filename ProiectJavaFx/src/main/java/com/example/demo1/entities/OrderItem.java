package com.example.demo1.entities;

public class OrderItem {
    private int idOrderItem;
    private int idOrder;
    private int idProduct;
    private int productQuantity;
    private double productPrice;

    public OrderItem(int idOrderItem, int idOrder, int idProduct, int productQuantity, double productPrice) {
        this.idOrderItem = idOrderItem;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public OrderItem(int idProduct, double productPrice) {
        this.idProduct = idProduct;
        this.productPrice = productPrice;
    }

    public int getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(int idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
