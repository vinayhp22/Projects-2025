package com.food.servlet;

import com.food.dao.OrderDAO;
import com.food.daoimpl.OrderDAOImpl;
import com.food.model.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewOrders")
public class ViewOrdersServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAOImpl(); // Initialize your DAO implementation
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> orders = orderDAO.getAllOrders(); // Fetch all orders from the database

        for(Order order: orders) {
            System.out.println("View Orders: " + order.getOrderId());
        }

        request.setAttribute("orders", orders); // Set orders in the request scope
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view_orders.jsp");
        dispatcher.forward(request, response); // Forward to the JSP page
    }
}
