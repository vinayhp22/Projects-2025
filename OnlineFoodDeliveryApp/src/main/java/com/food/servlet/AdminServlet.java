package com.food.servlet;

import com.food.dao.RestaurantDAO;
import com.food.dao.MenuDAO;
import com.food.daoimpl.RestaurantDAOImpl;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.Restaurant;
import com.food.model.Menu;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private RestaurantDAO restaurantDAO;
    private MenuDAO menuDAO;

    @Override
    public void init() {
        restaurantDAO = new RestaurantDAOImpl();
        menuDAO = new MenuDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request, response)) {
            return;
        }

        String action = request.getParameter("action");

        if (action == null) {
            action = "dashboard"; // Default action
        }

        switch (action) {
            case "dashboard":
                showDashboard(request, response);
                break;
            case "viewRestaurants":
                viewRestaurants(request, response);
                break;
            case "viewMenus":
                viewMenus(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid Action");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request, response)) {
            return;
        }

        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action required");
            return;
        }

        switch (action) {
            case "addRestaurant":
                addRestaurant(request, response);
                break;
            case "addMenu":
                addMenu(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid Action");
                break;
        }
    }

    private boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login.jsp");
            return true;
        }

        return false;
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void viewRestaurants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
        request.setAttribute("restaurants", restaurants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_restaurants.jsp");
        dispatcher.forward(request, response);
    }

    private void viewMenus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Menu> menus = menuDAO.getAllMenus();
        request.setAttribute("menus", menus);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_menus.jsp");
        dispatcher.forward(request, response);
    }

    private void addRestaurant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String cuisine = request.getParameter("cuisine");
        String address = request.getParameter("address");

        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setCuisineType(cuisine);
        restaurant.setAddress(address);

        restaurantDAO.addRestaurant(restaurant);
        response.sendRedirect("admin?action=viewRestaurants");
    }

    private void addMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        String itemName = request.getParameter("itemName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        Menu menu = new Menu();
        menu.setRestaurantId(restaurantId);
        menu.setItemName(itemName);
        menu.setDescription(description);
        menu.setPrice(price);

        menuDAO.addMenu(menu);
        response.sendRedirect("admin?action=viewMenus");
    }
}
