package com.example.demo1.entities;

import java.sql.Date;
import java.sql.Time;

public class Order {
    private int idOrder;
    private int idCustomer;
    private Date orderDate;
    private Time orderTime;
    private int totalPrice;
    private String status;

    public Order(){}

    public Order(int idOrder, int idCustomer, Date orderDate, Time orderTime, int totalPrice, String status) {
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order(int idOrder, Date orderDate, Time orderTime,int idCustomer,String status) {
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.idCustomer = idCustomer;
        this.status=status;
    }
    public Order(int idOrder, Date orderDate,int totalPrice,String status) {
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status=status;
    }
    public Order(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Order(int idCustomer, Date orderDate) {
        this.idCustomer = idCustomer;
        this.orderDate = orderDate;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder= " +  idOrder+
                ", idCustomer = " + idCustomer +
                ", Data= " + orderDate +
                ", orderTime= " + orderTime +
                ", totalPrice= " + totalPrice +
                ", status= " + status + '}';
    }
}
