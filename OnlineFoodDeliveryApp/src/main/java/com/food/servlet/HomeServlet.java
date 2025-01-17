package com.food.servlet;

import com.food.model.Restaurant;
import com.food.dao.RestaurantDAO;
import com.food.daoimpl.RestaurantDAOImpl;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")  // Mapping the servlet to the URL 'home'
public class HomeServlet extends HttpServlet {

    private RestaurantDAO restaurantDAO;

    @Override
    public void init() {
        // Initialize the DAO
        restaurantDAO = new RestaurantDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch list of restaurants
        List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();

        // Set the restaurant list as a request attribute
        request.setAttribute("restaurantList", restaurantList);

        System.out.println("asdfasdfg");

        // Forward to home.jsp

        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.include(request, response);
    }
}