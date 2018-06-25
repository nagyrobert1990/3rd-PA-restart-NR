package com.codecool.web.dao.database;

import com.codecool.web.dao.MyOrdersDao;
import com.codecool.web.model.MyOrders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMyOrdersDao extends AbstractDao implements MyOrdersDao {

    public DatabaseMyOrdersDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<MyOrders> findAllOfMyOrders(String customerId) throws SQLException {
        String sql = "SELECT orders.order_id, " +
                "SUM(order_details.quantity) AS num_of_products_ordered, " +
                "SUM(order_details.quantity * order_details.unit_price) AS total_price " +
                "FROM order_details " +
                "JOIN orders ON orders.order_id = order_details.order_id " +
                "JOIN products ON order_details.product_id = products.product_id " +
                "WHERE customer_id = ? " +
                "GROUP BY orders.order_id;";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()){
                List<MyOrders> myOrders = new ArrayList<>();
                while (resultSet.next()) {
                    myOrders.add(fetchMyOrders(resultSet));
                }
                return myOrders;
            }
        }
    }

    @Override
    public void order(int productId, int quantity, int orderId) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO order_details (order_id, product_id, unit_price, quantity, discount) " +
                "VALUES (?, ?, (SELECT unit_price FROM products WHERE product_id = ?), ?, 0);";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            statement.setInt(3, productId);
            statement.setInt(4, quantity);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void addToOrders(int orderId, String customerId) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO orders (order_id, customer_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, orderId);
            statement.setString(2, customerId);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public int getNewOrderId() throws SQLException {
        int newOrderId = 0;
        String sql = "SELECT MAX(order_id) AS order_id FROM order_details;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                newOrderId = (resultSet.getInt("order_id") + 1);
                return newOrderId;
            }
        }
        return 0;
    }

    private MyOrders fetchMyOrders(ResultSet resultSet) throws SQLException {
        int orderId = resultSet.getInt("order_id");
        int numOfProductsOrdered = resultSet.getInt("num_of_products_ordered");
        float totalPrice = resultSet.getFloat("total_price");
        return new MyOrders(Integer.toString(orderId), numOfProductsOrdered, totalPrice);
    }
}
