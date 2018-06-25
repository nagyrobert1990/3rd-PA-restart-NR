package com.codecool.web.dao.database;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseProductDao extends AbstractDao implements ProductDao {

    public DatabaseProductDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Product> findAllAvailableProducts() throws SQLException {
        String sql = "SELECT product_id, product_name, unit_price, units_in_stock, categories.category_name AS category_name, suppliers.company_name AS supplier_name " +
                "FROM products " +
                "JOIN categories ON products.category_id = categories.category_id " +
                "JOIN suppliers ON products.supplier_id = suppliers.supplier_id;";
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(fetchProduct(resultSet));
            }
            return products;
        }
    }

    @Override
    public List<Product> findProductsByOrderId(String orderId) throws SQLException {
        String sql = "SELECT order_details.product_id, products.product_name, order_details.unit_price, products.units_in_stock, categories.category_name AS category_name, suppliers.company_name AS supplier_name " +
                "FROM products " +
                "JOIN order_details ON products.product_id = order_details.product_id " +
                "JOIN categories ON products.category_id = categories.category_id " +
                "JOIN suppliers ON products.supplier_id = suppliers.supplier_id " +
                "WHERE order_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(orderId));
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    products.add(fetchProduct(resultSet));
                }
                return products;
            }
        }
    }

    private Product fetchProduct(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        float unitPrice = resultSet.getFloat("unit_price");
        int unitsInStock = resultSet.getInt("units_in_stock");
        String categoryName = resultSet.getString("category_name");
        String supplierName = resultSet.getString("supplier_name");
        return new Product(Integer.toString(productId), productName, unitPrice, unitsInStock, categoryName, supplierName);
    }
}