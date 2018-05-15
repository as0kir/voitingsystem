package ru.askir.voitingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.askir.voitingsystem.model.Restaurant;
import ru.askir.voitingsystem.repository.RestaurantRepository;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository repository;

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
