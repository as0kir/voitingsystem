package ru.askir.voitingsystem.service;

import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.util.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface VoiteService {
    List<Voite> getAll();
    List<Voite> getAll(LocalDate dateSet);
    Voite get(int idUser, LocalDate dateSet);
    void setVoite(int idUser, int idMenu);
}
