package ru.askir.voitingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.repository.DishRepository;
import ru.askir.voitingsystem.util.exception.NotFoundException;

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
    public Dish get(int id, int menuId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, menuId), id);
    }

    @Override
    public void delete(int id, int menuId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, menuId), id);
    }

    @Override
    public List<Dish> getAll(int menuId) {
        return repository.getAll(menuId);
    }

    @Override
    public Dish update(Dish dish, int menuId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(dish, menuId), dish.getId());
    }

    @Override
    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, menuId);
    }

    @Override
    public Dish getWithMenu(int id, int menuId) {
        return checkNotFoundWithId(repository.getWithMenu(id, menuId), id);
    }    
}
