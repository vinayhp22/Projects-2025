package com.food.servlet;

import com.food.dao.UserDAO;
import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Collect user data from request
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Hash the password for real application
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        // Create User object and save using DAO
        User newUser = new User(username, password, email, address, "Customer");
        userDAO.addUser(newUser);

        response.sendRedirect("login.jsp");
    }
}