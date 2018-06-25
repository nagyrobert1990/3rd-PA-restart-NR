package com.codecool.web.service.simple;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.model.Product;
import com.codecool.web.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class SimpleProductService implements ProductService {

    private final ProductDao productDao;

    public SimpleProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllAvailableProduct() throws SQLException {
        return productDao.findAllAvailableProducts();
    }

    @Override
    public List<Product> getProductsByOrderId(String orderId) throws SQLException {
        return productDao.findProductsByOrderId(orderId);
    }
}
