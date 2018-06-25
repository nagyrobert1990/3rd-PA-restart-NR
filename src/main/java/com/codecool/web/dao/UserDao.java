package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.SQLException;

public interface UserDao {

    User findById(String id) throws SQLException;
}
