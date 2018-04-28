package ru.askir.voitingsystem.repository;

import ru.askir.voitingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant user);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();
}
