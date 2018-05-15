package ru.askir.voitingsystem.service;

import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.util.List;

public interface DishService {
    Dish get(int id, int menuId) throws NotFoundException;

    void delete(int id, int menuId) throws NotFoundException;

    List<Dish> getAll(int menuId);

    Dish update(Dish menu, int menuId) throws NotFoundException;

    Dish create(Dish menu, int menuId);

    Dish getWithMenu(int id, int menuId);
}
