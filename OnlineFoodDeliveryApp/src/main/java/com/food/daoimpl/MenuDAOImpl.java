package com.food.daoimpl;

import com.food.dao.MenuDAO;
import com.food.model.Menu;
import com.food.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {

    @Override
    public void addMenu(Menu menu) {
        String sql = "INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setBoolean(5, menu.getIsAvailable());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        String sql = "SELECT * FROM Menu WHERE MenuID = ?";
        Menu menu = null;
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    menu = extractMenuFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        String sql = "UPDATE Menu SET RestaurantID = ?, ItemName = ?, Description = ?, Price = ?, IsAvailable = ? WHERE MenuID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setBoolean(5, menu.getIsAvailable());
            pstmt.setInt(6, menu.getMenuId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        String sql = "DELETE FROM Menu WHERE MenuID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public List<Menu> getAllMenusByRestaurant(int restaurantId) {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM Menu WHERE RestaurantID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, restaurantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Menu menu = extractMenuFromResultSet(rs);
                    menus.add(menu);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return menus;
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM Menu";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

//            pstmt.setInt(1, restaurantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Menu menu = extractMenuFromResultSet(rs);
                    menus.add(menu);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return menus;
    }

    private Menu extractMenuFromResultSet(ResultSet rs) throws SQLException {
        Menu menu = new Menu();
        menu.setMenuId(rs.getInt("MenuID"));
        menu.setRestaurantId(rs.getInt("RestaurantID"));
        menu.setItemName(rs.getString("ItemName"));
        menu.setDescription(rs.getString("Description"));
        menu.setPrice(rs.getDouble("Price"));
        menu.setAvailable(rs.getBoolean("IsAvailable"));
        return menu;
    }
}