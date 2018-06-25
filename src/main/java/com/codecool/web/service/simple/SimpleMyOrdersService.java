package com.codecool.web.service.simple;

import com.codecool.web.dao.MyOrdersDao;
import com.codecool.web.model.MyOrders;
import com.codecool.web.service.MyOrdersService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SimpleMyOrdersService implements MyOrdersService {

    private final MyOrdersDao myOrdersDao;

    public SimpleMyOrdersService(MyOrdersDao myOrdersDao) {
        this.myOrdersDao = myOrdersDao;
    }

    @Override
    public List<MyOrders> getAllOrdersByCustomerId(String customerId) throws SQLException {
        return myOrdersDao.findAllOfMyOrders(customerId);
    }

    @Override
    public void addNewOrder(String[] productIds, String[] quantities, String customerId) throws SQLException, ServiceException {
        productIds = productIds[0].split(",");
        quantities = quantities[0].split(",");
        int[] productIdsInt = Arrays.stream(productIds).mapToInt(Integer::parseInt).toArray();
        int[] quantitiesInt = Arrays.stream(quantities).mapToInt(Integer::parseInt).toArray();

        int newOrderId = myOrdersDao.getNewOrderId();
        myOrdersDao.addToOrders(newOrderId, customerId);

        for (int i = 0; i < productIdsInt.length; i++) {
            myOrdersDao.order(productIdsInt[i], quantitiesInt[i], newOrderId);
        }
    }
}
