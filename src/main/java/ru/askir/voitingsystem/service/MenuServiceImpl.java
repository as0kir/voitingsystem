package ru.askir.voitingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.repository.MenuRepository;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository repository;

    @Autowired
    public MenuServiceImpl(MenuRepository repository) {
        this.repository = repository;
    }

    @Override
    public Menu get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(menu, restaurantId), menu.getId());
    }

    @Override
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, restaurantId);
    }

    @Override
    public Menu getWithRestaurant(int id, int restaurantId) {
        return checkNotFoundWithId(repository.getWithResraurant(id, restaurantId), id);
    }
}
