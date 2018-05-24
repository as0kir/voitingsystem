package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.model.Restaurant;
import ru.askir.voitingsystem.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {
    @Autowired
    CrudDishRepository crudDishRepository;

    @Autowired
    CrudMenuRepository crudMenuRepository;

    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }

        Menu menu = crudMenuRepository.findById(menuId).orElse(null);
        if(menu == null) {
            return null;
        }
        dish.setMenu(menu);
        return crudDishRepository.save(dish);
    }

    @Override
    public Dish save(Dish dish, int restaurantId, LocalDate dateSet) {
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }
        Menu menu = crudMenuRepository.get(restaurantId, dateSet);
        if(menu == null) {

            Restaurant restaurant = crudRestaurantRepository.findById(restaurantId).orElse(null);
            if(restaurant == null)
                return null;

            menu = new Menu(dateSet);
            menu.setRestaurant(restaurant);
            crudMenuRepository.save(menu);
        }

        dish.setMenu(menu);
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
    public List<Dish> getAll(LocalDate dateSet) {
        return crudDishRepository.getAll(dateSet);
    }

    @Override
    public Dish getWithMenu(int id, int menuId) {
        return crudDishRepository.getWithMenu(id, menuId);
    }
    
}
