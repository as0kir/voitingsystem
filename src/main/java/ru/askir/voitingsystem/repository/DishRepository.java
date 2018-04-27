package ru.askir.voitingsystem.repository;

import ru.askir.voitingsystem.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int menuId);

    boolean delete(int id, int menuId);

    Dish get(int id, int menuId);

    List<Dish> getAll(int menuId);

    default Dish getWithMenu(int id, int menuId) {
        throw new UnsupportedOperationException();
    }    
}
