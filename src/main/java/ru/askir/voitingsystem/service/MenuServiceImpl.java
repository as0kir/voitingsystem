package ru.askir.voitingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.repository.DishRepository;
import ru.askir.voitingsystem.repository.MenuRepository;
import ru.askir.voitingsystem.to.MenuTo;
import ru.askir.voitingsystem.util.DateTimeUtil;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.checkNotFound;
import static ru.askir.voitingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private final DishRepository dishRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, DishRepository dishRepository) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Menu get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(menuRepository.get(id, restaurantId), id);
    }

    @Override
    public Menu getByDateSet(LocalDate dateSet, int restaurantId) throws NotFoundException {
        return checkNotFound(menuRepository.getByDateSet(dateSet, restaurantId), "dateSet = " + dateSet);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id, restaurantId), id);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return menuRepository.getAll(restaurantId);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(menuRepository.save(menu, restaurantId), menu.getId());
    }

    @Override
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return menuRepository.save(menu, restaurantId);
    }

    @Override
    public Menu getWithRestaurant(int id, int restaurantId) {
        return checkNotFoundWithId(menuRepository.getWithResraurant(id, restaurantId), id);
    }

    @Override
    public List<MenuTo> getAllWithVoites(LocalDate dateSet) {
        List<MenuTo> menuToList = menuRepository.getAllWithVoites(dateSet);

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < menuToList.size(); i++) {
            hashMap.put(menuToList.get(i).getMenuId(), i);
        }

        List<Dish> dishList = dishRepository.getAll(dateSet);
        dishList.forEach(dish -> menuToList.get(hashMap.get(dish.getMenu().getId())).getDishes().add(dish));

        return menuToList;
    }

    @Override
    public List<MenuTo> getAllWithVoites() {
        return getAllWithVoites(DateTimeUtil.getCurrentDate());
    }
}
