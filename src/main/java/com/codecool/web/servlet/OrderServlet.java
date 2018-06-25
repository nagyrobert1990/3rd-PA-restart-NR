package com.codecool.web.servlet;

import com.codecool.web.dao.MyOrdersDao;
import com.codecool.web.dao.database.DatabaseMyOrdersDao;
import com.codecool.web.model.User;
import com.codecool.web.service.MyOrdersService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleMyOrdersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/protected/order")
public class OrderServlet extends AbstractServlet {

    private final Logger logger = LoggerFactory.getLogger(OrderServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            MyOrdersDao myOrdersDao = new DatabaseMyOrdersDao(connection);
            MyOrdersService myOrdersService = new SimpleMyOrdersService(myOrdersDao);

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            String customerId = user.getId();

            String[] productIds = req.getParameterValues("productIds");
            String[] quantities = req.getParameterValues("quantities");

            myOrdersService.addNewOrder(productIds, quantities, customerId);

            sendMessage(resp, HttpServletResponse.SC_OK, "New Order Successfully Added");
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        } catch (ServiceException e) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
