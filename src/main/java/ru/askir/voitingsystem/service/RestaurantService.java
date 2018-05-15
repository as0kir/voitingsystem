package ru.askir.voitingsystem.service;

import ru.askir.voitingsystem.model.Restaurant;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {
    Restaurant create(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    void update(Restaurant restaurant);

    List<Restaurant> getAll();
}
