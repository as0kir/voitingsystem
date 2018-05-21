package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "dateSet");

    @Autowired
    CrudDishRepository crudDishRepository;

    @Autowired
    CrudMenuRepository crudMenuRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }
        dish.setMenu(crudMenuRepository.getOne(menuId));
        return crudDishRepository.save(dish);
    }

    @Override
    public Dish save(Dish dish, int restaurantId, LocalDate dateSet) {
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }
        dish.setMenu(crudMenuRepository.DateSetEqualsAndRestaurant_IdEquals(dateSet, restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudDishRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll(int menuId) {
        return crudDishRepository.getAll(menuId);
    }

    @Override
    public List<Dish> getAll(int restaurantId, LocalDate dateSet) {
        return crudDishRepository.getAll(restaurantId, dateSet);
    }

    @Override
    public Dish getWithMenu(int id, int menuId) {
        return crudDishRepository.getWithMenu(id, menuId);
    }
    
}
