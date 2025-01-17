package com.food.servlet;

import com.food.dao.RestaurantDAO;
import com.food.daoimpl.RestaurantDAOImpl;
import com.food.model.Restaurant;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addRestaurant")
public class AddRestaurantServlet extends HttpServlet {

    private RestaurantDAO restaurantDAO;

    @Override
    public void init() {
        restaurantDAO = new RestaurantDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add_restaurant.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String cuisineType = request.getParameter("cuisineType");
        String address = request.getParameter("address");
        int deliveryTime = Integer.parseInt(request.getParameter("deliveryTime"));
        int adminUserId = Integer.parseInt(request.getParameter("adminUserId"));

        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setCuisineType(cuisineType);
        restaurant.setAddress(address);
        restaurant.setDeliveryTime(deliveryTime);
        restaurant.setAdminUserId(adminUserId);

        restaurantDAO.addRestaurant(restaurant);
        response.sendRedirect("admin_dashboard.jsp");
    }
}
