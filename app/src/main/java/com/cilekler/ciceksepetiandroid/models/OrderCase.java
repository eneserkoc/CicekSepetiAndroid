package com.cilekler.ciceksepetiandroid.models;

public class OrderCase {

    private ShopModel shop;
    private int orderNumber;
    private double distance;

    public OrderCase(ShopModel shop, int orderNumber, double distance) {
        this.distance = distance;
        this.shop = shop;
        this.orderNumber = orderNumber;
    }

    public ShopModel getShop() {
        return shop;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public double getDistance() {
        return distance;
    }
}
