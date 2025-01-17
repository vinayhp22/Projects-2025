package com.food.servlet;

import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.Menu;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() {
        menuDAO = new MenuDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantId = request.getParameter("restaurantId");
        if (restaurantId != null) {
            try {
                List<Menu> menuList = menuDAO.getAllMenusByRestaurant(Integer.parseInt(restaurantId));
                request.setAttribute("menuList", menuList);
            } catch (NumberFormatException e) {
                // Handle exception (log and/or forward to an error page)
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
        dispatcher.forward(request, response);
    }
}