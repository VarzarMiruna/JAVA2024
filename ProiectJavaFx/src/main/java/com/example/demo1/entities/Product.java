package com.example.demo1.entities;

public class Product {
    private int idProduct;
    private String name;
    private String description;
    private int price;
    private int stock;
    private String category;

    public Product(){}

    public Product(int idProduct, String name, String description, int price, int stock, String category) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;

    }

    public Product(int idProduct, String name, String description, int price) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getIdProduct() == product.getIdProduct() && getName().equals(product.getName());
    }

    public Product(int idProduct, String name, int price) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id: " + idProduct +
                ", name: " + name +
                ", description " + description +
                ", price: " + price +
                ", stock: " + stock +
                ", category: " + category +
                '}';
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
