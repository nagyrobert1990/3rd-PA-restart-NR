package com.codecool.web.servlet;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.dao.database.DatabaseProductDao;
import com.codecool.web.model.Product;
import com.codecool.web.model.User;
import com.codecool.web.service.ProductService;
import com.codecool.web.service.simple.SimpleProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/details")
public class DetailsServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())){
            ProductDao productDao = new DatabaseProductDao(connection);
            ProductService productService = new SimpleProductService(productDao);

            String id = req.getParameter("id");

            List<Product> products = productService.getProductsByOrderId(id);

            sendMessage(resp, HttpServletResponse.SC_OK, products);
        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }
}
