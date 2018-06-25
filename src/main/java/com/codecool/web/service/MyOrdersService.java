package com.codecool.web.service;

import com.codecool.web.model.MyOrders;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface MyOrdersService {

    List<MyOrders> getAllOrdersByCustomerId(String customerId) throws SQLException;
    void addNewOrder(String[] productId, String[] quantity, String customerId) throws SQLException, ServiceException;
}
