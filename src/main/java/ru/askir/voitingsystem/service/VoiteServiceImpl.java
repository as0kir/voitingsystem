package ru.askir.voitingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.repository.VoiteRepository;
import ru.askir.voitingsystem.util.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.checkDate;
import static ru.askir.voitingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoiteServiceImpl implements VoiteService {

    private final VoiteRepository repository;

    @Autowired
    public VoiteServiceImpl(VoiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Voite> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Voite> getAll(LocalDate dateSet) {
        return repository.getAll(dateSet);
    }

    @Override
    public void setVoite(int idUser, int idMenu) {
        //checkDate()
        repository.setVoite(idUser, idMenu);
    }
}
