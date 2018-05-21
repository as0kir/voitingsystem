package ru.askir.voitingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.repository.DishRepository;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Dish> getAll(int menuId) {
        return repository.getAll(menuId);
    }

    @Override
    public List<Dish> getAll(int restaurantId, LocalDate dateSet) {
        return repository.getAll(restaurantId, dateSet);
    }

    @Override
    public Dish update(Dish dish, int menuId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(dish, menuId), dish.getId());
    }

    @Override
    public Dish update(Dish dish, int restaurantId, LocalDate dateSet) throws NotFoundException {
        return checkNotFoundWithId(repository.save(dish, restaurantId, dateSet), dish.getId());
    }

    @Override
    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, menuId);
    }

    @Override
    public Dish create(Dish dish, int restaurantId, LocalDate dateSet) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId, dateSet);
    }

    @Override
    public Dish getWithMenu(int id, int menuId) {
        return checkNotFoundWithId(repository.getWithMenu(id, menuId), id);
    }    
}
