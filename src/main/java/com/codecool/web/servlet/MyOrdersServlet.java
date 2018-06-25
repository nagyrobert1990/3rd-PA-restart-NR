package com.codecool.web.servlet;

import com.codecool.web.dao.MyOrdersDao;
import com.codecool.web.dao.database.DatabaseMyOrdersDao;
import com.codecool.web.model.MyOrders;
import com.codecool.web.model.User;
import com.codecool.web.service.MyOrdersService;
import com.codecool.web.service.simple.SimpleMyOrdersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/orders")
public class MyOrdersServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())){
            MyOrdersDao myOrdersDao = new DatabaseMyOrdersDao(connection);
            MyOrdersService myOrdersService = new SimpleMyOrdersService(myOrdersDao);

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            String id = user.getId();

            List<MyOrders> myOrders = myOrdersService.getAllOrdersByCustomerId(id);

            sendMessage(resp, HttpServletResponse.SC_OK, myOrders);
        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }
}
