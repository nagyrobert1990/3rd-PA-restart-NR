package com.codecool.web.model;

public class MyOrders extends AbstractModel {

    private final int numOfProductsOrdered;
    private final float totalPrice;

    public MyOrders(String id, int numOfProductsOrdered, float totalPrice) {
        super(id);
        this.numOfProductsOrdered = numOfProductsOrdered;
        this.totalPrice = totalPrice;
    }

    public int getNumOfProductsOrdered() {
        return numOfProductsOrdered;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
