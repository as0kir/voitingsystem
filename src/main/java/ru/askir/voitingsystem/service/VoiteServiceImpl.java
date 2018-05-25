package ru.askir.voitingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.repository.VoiteRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.checkNotAllowOperation;

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
        checkNotAllowOperation(repository.setVoite(idUser, idMenu));
    }

    @Override
    public Voite get(int idUser, LocalDate dateSet) {
        return repository.get(idUser, dateSet);
    }
}
