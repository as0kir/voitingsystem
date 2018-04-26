package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.askir.voitingsystem.model.Restaurant;
import ru.askir.voitingsystem.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id)!=0;
    }

    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }
}
