package com.food.servlet;

import com.food.dao.RestaurantDAO;
import com.food.daoimpl.RestaurantDAOImpl;
import com.food.model.Restaurant;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet({"/viewRestaurants", "/updateRestaurant"})
public class ViewRestaurantsServlet extends HttpServlet {

    private RestaurantDAO restaurantDAO;

    @Override
    public void init() {
        restaurantDAO = new RestaurantDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteRestaurant(request, response);
        } else if ("edit".equals(action)) {
            updateRestaurant(request, response);
        } else {
            listRestaurants(request, response);
        }
    }

    private void listRestaurants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch all restaurants from the database
            List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
            // Add the restaurants to the request attribute
            request.setAttribute("restaurants", restaurants);
            // Forward to the JSP for rendering
            RequestDispatcher dispatcher = request.getRequestDispatcher("view_restaurants.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load restaurants.");
        }
    }

    private void deleteRestaurant(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int restaurantId = Integer.parseInt(request.getParameter("id"));
            restaurantDAO.deleteRestaurant(restaurantId);
            response.sendRedirect("viewRestaurants");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete restaurant.");
        }
    }

    private void updateRestaurant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int restaurantId = Integer.parseInt(request.getParameter("id"));

        try {
            Restaurant restaurant = restaurantDAO.getRestaurantById(restaurantId);
            if (restaurant != null) {
                request.setAttribute("restaurant", restaurant);
                RequestDispatcher dispatcher = request.getRequestDispatcher("edit_restaurant.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Restaurant not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load restaurant for editing.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("updateRestaurant".equals(action)) {
            processUpdateRestaurant(request, response);
        }
    }

    private void processUpdateRestaurant(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        String name = request.getParameter("name");
        String cuisineType = request.getParameter("cuisineType");
        String address = request.getParameter("address");
        int deliveryTime = Integer.parseInt(request.getParameter("deliveryTime"));
        boolean isActive = request.getParameter("isActive") != null;

        String imagePath = null;
        String uploadDir = getServletContext().getRealPath("/images"); // Directory for images

        // Check if it's a multipart request
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Parse the request
                List<FileItem> formItems = upload.parseRequest(request);

                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        // Process the file upload
                        String fileName = new File(item.getName()).getName();
                        imagePath = fileName;
                        String filePath = uploadDir + File.separator + fileName;

                        // Save the file to the directory
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                    } else {
                        // Process regular form fields
                        switch (item.getFieldName()) {
                            case "name":
                                name = item.getString();
                                break;
                            case "cuisineType":
                                cuisineType = item.getString();
                                break;
                            case "address":
                                address = item.getString();
                                break;
                            case "deliveryTime":
                                deliveryTime = Integer.parseInt(item.getString());
                                break;
                            case "isActive":
                                isActive = "on".equals(item.getString());
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File upload failed.");
                return;
            }
        }

        try {
            // Update restaurant details
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(restaurantId);
            restaurant.setName(name);
            restaurant.setCuisineType(cuisineType);
            restaurant.setAddress(address);
            restaurant.setDeliveryTime(deliveryTime);
            restaurant.setActive(isActive);
            if (imagePath != null) {
                restaurant.setImagePath(imagePath); // Update only if a new image is uploaded
            }
            System.out.println(restaurant);
            restaurantDAO.updateRestaurant(restaurant);
            response.sendRedirect("view_restaurants");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update restaurant.");
        }
    }

}

