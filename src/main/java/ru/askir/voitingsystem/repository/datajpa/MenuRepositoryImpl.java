package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.model.Restaurant;
import ru.askir.voitingsystem.repository.MenuRepository;
import ru.askir.voitingsystem.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @Autowired
    CrudMenuRepository crudMenuRepository;

    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
        Restaurant restaurant = crudRestaurantRepository.findById(restaurantId).orElse(null);
        if(restaurant == null) {
            return null;
        }
        menu.setRestaurant(restaurant);
        return crudMenuRepository.save(menu);

    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMenuRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id).filter(menu -> menu.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public Menu getByDateSet(LocalDate dateSet, int restaurantId) {
        return crudMenuRepository.get(restaurantId, dateSet);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return crudMenuRepository.getAll(restaurantId);
    }

    @Override
    public Menu getWithResraurant(int id, int restaurantId) {
        return crudMenuRepository.getWithRestaurant(id, restaurantId);
    }

    @Override
    public List<MenuTo> getAllWithVoites(LocalDate dateSet) {
        return crudMenuRepository.getAllWithVoites(dateSet);
    }
}
