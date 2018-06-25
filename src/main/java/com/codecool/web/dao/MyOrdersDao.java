package com.codecool.web.dao;

import com.codecool.web.model.MyOrders;

import java.sql.SQLException;
import java.util.List;

public interface MyOrdersDao {

    List<MyOrders> findAllOfMyOrders(String customerId) throws SQLException;
    void order(int productId, int quantity, int orderId) throws SQLException;
    void addToOrders(int orderId, String customerId) throws SQLException;
    int getNewOrderId() throws SQLException;
}
