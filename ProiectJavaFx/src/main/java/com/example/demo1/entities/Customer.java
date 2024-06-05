package com.example.demo1.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int idCustomer;

    private String name;
    private String email;
    private String address;
    private String password;
    private String country;
    private String postalCode;

    public Customer() {
    }


    public Customer(int idCustomer, String name, String email, String address, String password, String country, String postalCode) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.country = country;
        this.postalCode = postalCode;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer= " + idCustomer +
                ", name= " + name +
                ", email= " + email +
                ", address= " + address +
                ", password= " + password +
                ", country= " + country +
                ", postalCode= " + postalCode +
                '}';
    }
}
