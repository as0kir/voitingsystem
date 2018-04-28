package ru.askir.voitingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.repository.VoiteRepository;
import ru.askir.voitingsystem.util.NotFoundException;

import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoiteServiceImpl implements VoiteService {

    private final VoiteRepository repository;

    @Autowired
    public VoiteServiceImpl(VoiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Voite get(int id, int userId, int menuId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId, menuId), id);
    }

    @Override
    public void delete(int id, int userId, int menuId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId, menuId), id);
    }

    @Override
    public List<Voite> getAll(int userId, int menuId) {
        return repository.getAll(userId, menuId);
    }

    @Override
    public Voite update(Voite menu, int userId, int menuId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(menu, userId, menuId), menu.getId());
    }

    @Override
    public Voite create(Voite menu, int userId, int menuId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, userId, menuId);
    }

    @Override
    public Voite getWithUserAndMenu(int id, int userId, int menuId) {
        return checkNotFoundWithId(repository.getWithUserAndMenu(id, userId, menuId), id);
    }
}
