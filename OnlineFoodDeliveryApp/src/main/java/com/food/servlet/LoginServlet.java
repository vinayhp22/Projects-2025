package com.food.servlet;

import com.food.dao.UserDAO;
import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // In a real app, hash and check the password

        User user = userDAO.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            if(user.getRole().equals("Customer")) {
                // Create a session and redirect to home page
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", user);
                response.sendRedirect("home");
            }else{
                // Create a session and redirect to home page
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", user);
//                response.sendRedirect("admin_dashboard.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin_dashboard.jsp");
                dispatcher.forward(request, response);

            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}