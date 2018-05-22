package ru.askir.voitingsystem.repository;

import ru.askir.voitingsystem.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int menuId);

    Dish save(Dish dish, int restaurantId, LocalDate dateSet);

    boolean delete(int id);

    Dish get(int id);

    List<Dish> getAll(int menuId);

    List<Dish> getAll(int restaurantId, LocalDate dateSet);

    List<Dish> getAll(LocalDate dateSet);

    default Dish getWithMenu(int id, int menuId) {
        throw new UnsupportedOperationException();
    }    
}
