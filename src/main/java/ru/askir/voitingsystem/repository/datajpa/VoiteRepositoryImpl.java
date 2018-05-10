package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.repository.VoiteRepository;
import ru.askir.voitingsystem.util.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VoiteRepositoryImpl implements VoiteRepository{

    @Autowired
    CrudVoiteRepository crudVoiteRepository;

    @Autowired
    CrudUserRepository crudUserRepository;

    @Autowired
    CrudMenuRepository crudMenuRepository;

    @Override
    public List<Voite> getAll() {
        return null; // crudVoiteRepository.getAll();
    }

    @Override
    public List<Voite> getAll(LocalDate dateSet) {
        return null; //crudVoiteRepository.getAll(dateSet);
    }

    @Override
    @Transactional
    public void setVoite(int idUser, int idMenu) {
        Voite voite = crudVoiteRepository.get(idUser, DateUtil.getCurrentDate());

        if(voite != null){
            if(voite.getMenu().getId() != idMenu) {
                voite.setMenu(crudMenuRepository.getOne(idMenu));
            }
        }
        else {
            Menu menu = crudMenuRepository.getOne(idMenu);
            if(menu.getDateSet() == DateUtil.getCurrentDate())
                voite = new Voite(crudUserRepository.getOne(idUser), crudMenuRepository.getOne(idMenu), LocalDateTime.now());
        }

        //crudVoiteRepository.setVoite(voite);
    }
}
