package ru.askir.voitingsystem.service;

import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface DishService {
    Dish get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Dish> getAll(int menuId);

    List<Dish> getAll(int restaurantId, LocalDate dateSet);

    Dish update(Dish dish, int menuId) throws NotFoundException;

    Dish update(Dish dish, int restaurantId, LocalDate dateSet) throws NotFoundException;

    Dish create(Dish dish, int menuId);

    Dish create(Dish dish, int restaurantId, LocalDate dateSet);

    Dish getWithMenu(int id, int menuId);
}
