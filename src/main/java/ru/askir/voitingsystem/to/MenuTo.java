package ru.askir.voitingsystem.to;

import ru.askir.voitingsystem.model.Dish;

import java.util.ArrayList;
import java.util.List;

public class MenuTo {
    private int restaurantId;

    private String restaurantName;

    private int menuId;

    private int countVoices;

    private List<Dish> dishes;

    public MenuTo(int restaurantId, String restaurantName, int menuId, int countVoices) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.menuId = menuId;
        this.countVoices = countVoices;
        dishes = new ArrayList<>();
    }

    public MenuTo() {
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCountVoices() {
        return countVoices;
    }

    public void setCountVoices(int countVoices) {
        this.countVoices = countVoices;
    }
}
