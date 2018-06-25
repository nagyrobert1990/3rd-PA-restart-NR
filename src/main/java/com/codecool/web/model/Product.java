package com.codecool.web.model;

public class Product extends AbstractModel {

    private final String productName;
    private final float unitPrice;
    private final int unitsInStock;
    private final String categoryName;
    private final String supplierName;

    public Product(String id, String productName, float unitPrice, int unitsInStock, String categoryName, String supplierName) {
        super(id);
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.categoryName = categoryName;
        this.supplierName = supplierName;
    }

    public Product(String id, String productName, float unitPrice) {
        super(id);
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = 0;
        this.categoryName = null;
        this.supplierName = null;
    }

    public String getProductName() {
        return productName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }
}
