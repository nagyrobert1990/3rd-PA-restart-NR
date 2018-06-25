package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Customer;
import com.codecool.web.model.Employee;
import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public User findById(String id) throws SQLException {
        if (id == null || "".equals(id)) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }
        String sql;
        if (id.matches("-?\\d+")) {
            sql = "SELECT employee_id, last_name, first_name FROM employees WHERE employee_id = ?;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(id));
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return fetchEmployee(resultSet);
                    }
                }
            }
        } else {
            sql = "SELECT customer_id, company_name, contact_name FROM customers WHERE customer_id = ?;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return fetchCustomer(resultSet);
                    }
                }
            }
        }
        return null;
    }

    private Customer fetchCustomer(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("customer_id");
        String companyName = resultSet.getString("company_name");
        String contactName = resultSet.getString("contact_name");
        return new Customer(id, companyName, contactName);
    }

    private Employee fetchEmployee(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("employee_id");
        String lastName = resultSet.getString("last_name");
        String firstName = resultSet.getString("first_name");
        return new Employee(Integer.toString(id), lastName, firstName);
    }
}
