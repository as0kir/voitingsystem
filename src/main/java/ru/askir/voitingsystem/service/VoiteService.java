package ru.askir.voitingsystem.service;

import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.util.NotFoundException;

import java.util.List;

public interface VoiteService {
    Voite get(int id, int userId, int menuId) throws NotFoundException;

    void delete(int id, int userId, int menuId) throws NotFoundException;

    List<Voite> getAll(int userId, int menuId);

    Voite update(Voite menu, int userId, int menuId) throws NotFoundException;

    Voite create(Voite menu, int userId, int menuId);

    Voite getWithUserAndMenu(int id, int userId, int menuId);
}
