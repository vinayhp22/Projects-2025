package com.food.servlet;

import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.Menu;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewMenus")
public class ViewMenusServlet extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() {
        menuDAO = new MenuDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String restaurantIdParam = request.getParameter("restaurantId");
        List<Menu> menus;

        try {
            if (restaurantIdParam != null && !restaurantIdParam.isEmpty()) {
                // Fetch menus for the specific restaurant
                int restaurantId = Integer.parseInt(restaurantIdParam);
                menus = menuDAO.getAllMenusByRestaurant(restaurantId);
            } else {
                // Fetch all menus if no filter is applied
                menus = menuDAO.getAllMenus();
            }

            // Add the menus to the request attribute
            request.setAttribute("menus", menus);
            // Forward to the JSP for rendering
            RequestDispatcher dispatcher = request.getRequestDispatcher("view_menus.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load menus.");
        }
    }
}
