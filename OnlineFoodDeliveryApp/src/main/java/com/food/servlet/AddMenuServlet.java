package com.food.servlet;

import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.Menu;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addMenu")
public class AddMenuServlet extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() {
        menuDAO = new MenuDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add_menu.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        String itemName = request.getParameter("itemName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        boolean isAvailable = Boolean.parseBoolean(request.getParameter("getIsAvailable"));

        Menu menu = new Menu();
        menu.setRestaurantId(restaurantId);
        menu.setItemName(itemName);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setAvailable(isAvailable);

        menuDAO.addMenu(menu);
        response.sendRedirect("admin_dashboard.jsp");
    }
}
