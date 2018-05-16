package ru.askir.voitingsystem.repository;

import ru.askir.voitingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu, int restaurantId);

    boolean delete(int id, int restaurantId);

    Menu get(int id, int restaurantId);

    Menu getByDateSet(LocalDate dateSet, int restaurantId);

    List<Menu> getAll(int restaurantId);

    default Menu getWithResraurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }
}
