package ru.askir.voitingsystem.service;

import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {
    Menu get(int id, int restaurantId) throws NotFoundException;

    Menu getByDateSet(LocalDate dateSet, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    List<Menu> getAll(int restaurantId);

    Menu update(Menu menu, int restaurantId) throws NotFoundException;

    Menu create(Menu menu, int restaurantId);

    Menu getWithRestaurant(int id, int restaurantId);
}
