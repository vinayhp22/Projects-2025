package com.food.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.model.Order;

@WebServlet("/orderConfirmation")
public class OrderConfirmationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Order order = (Order) session.getAttribute("order");

        if (order != null) {
            request.setAttribute("order", order);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order_confirmation.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirect to the home page or an error page if no order is found in the session
            response.sendRedirect("home.jsp"); // or a custom error page
        }
    }
}