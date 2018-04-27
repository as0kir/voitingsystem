package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.repository.DishRepository;

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
        if (!dish.isNew() && get(dish.getId(), menuId) == null) {
            return null;
        }
        dish.setMenu(crudMenuRepository.getOne(menuId));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int menuId) {
        return crudDishRepository.delete(id, menuId) != 0;
    }

    @Override
    public Dish get(int id, int menuId) {
        return crudDishRepository.findById(id).filter(dish -> dish.getMenu().getId() == menuId).orElse(null);
    }

    @Override
    public List<Dish> getAll(int menuId) {
        return crudDishRepository.getAll(menuId);
    }

    @Override
    public Dish getWithMenu(int id, int menuId) {
        return crudDishRepository.getWithMenu(id, menuId);
    }
    
}
