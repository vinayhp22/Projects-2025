package com.food.dao;

import com.food.model.Menu;
import java.util.List;

public interface MenuDAO {
    void addMenu(Menu menu);
    Menu getMenu(int menuId);
    void updateMenu(Menu menu);
    void deleteMenu(int menuId);
    List<Menu> getAllMenusByRestaurant(int restaurantId);
    List<Menu> getAllMenus();
}