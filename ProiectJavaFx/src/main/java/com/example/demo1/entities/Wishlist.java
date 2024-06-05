package com.example.demo1.entities;


public class Wishlist {
    private int idWishlist;
    private int idCustomer;
    private int idProduct;

    public Wishlist(){}
    public Wishlist(int idWishlist, int idCustomer, int idProduct) {
        this.idWishlist = idWishlist;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
    }

    public int getIdWishlist() {
        return idWishlist;
    }

    public void setIdWishlist(int idWishlist) {
        this.idWishlist = idWishlist;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "WishingList{" +
                "idWishlist= " +  idWishlist +
                ", idCustomer = " + idCustomer +
                ", idProduct= " + idProduct +'}';
    }
}
