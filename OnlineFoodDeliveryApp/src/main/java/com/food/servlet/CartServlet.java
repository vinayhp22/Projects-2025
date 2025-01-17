package com.food.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.Cart;
import com.food.model.CartItem;
import com.food.model.Menu;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addItemToCart(request, cart);
        } else if ("update".equals(action)) {
            updateCartItem(request, cart);
        } else if ("remove".equals(action)) {
            removeItemFromCart(request, cart);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

//    private void addItemToCart(HttpServletRequest request, Cart cart) {
//        int itemId = Integer.parseInt(request.getParameter("itemId"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//
//        // Assuming you have a method to get item details like price, name, etc.
//        CartItem item = new CartItem(itemId, "Item Name", quantity, price);
//        cart.addItem(item);
//    }


    private void addItemToCart(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));



        MenuDAO menuDAO = new MenuDAOImpl();
        Menu menuItem = menuDAO.getMenu(itemId);

        HttpSession session = request.getSession();
        session.setAttribute("resturantId", menuItem.getRestaurantId());

        if (menuItem != null) {
            CartItem item = new CartItem(
                    menuItem.getMenuId(),
                    menuItem.getRestaurantId(),
                    menuItem.getItemName(),
                    quantity,
                    menuItem.getPrice()
            );
            cart.addItem(item);
        }
    }





    private void updateCartItem(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cart.updateItem(itemId, quantity);
    }

    private void removeItemFromCart(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        cart.removeItem(itemId);
    }
}
