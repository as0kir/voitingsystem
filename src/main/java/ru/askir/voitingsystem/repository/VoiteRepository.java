package ru.askir.voitingsystem.repository;

import ru.askir.voitingsystem.model.Voite;

import java.time.LocalDate;
import java.util.List;

public interface VoiteRepository {
    List<Voite> getAll();
    List<Voite> getAll(LocalDate dateSet);
    void setVoite(int idUser, int idMenu);
}