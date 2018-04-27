package ru.askir.voitingsystem.repository;

import ru.askir.voitingsystem.model.Voite;

import java.util.List;

public interface VoiteRepository {
    Voite save(Voite voite, int userId, int menuId);

    boolean delete(int id, int userId, int menuId);

    Voite get(int id, int userId, int menuId);

    List<Voite> getAll(int userId, int menuId);

    default Voite getWithUserAndMenu(int id, int userId, int menuId) {
        throw new UnsupportedOperationException();
    }
}