package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.repository.VoiteRepository;
import ru.askir.voitingsystem.util.DateTimeUtil;

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

    @Autowired
    DateTimeUtil dateTimeUtil;

    @Override
    public List<Voite> getAll() {
        return crudVoiteRepository.getAll(dateTimeUtil.getCurrentDate());
    }

    @Override
    public List<Voite> getAll(LocalDate dateSet) {
        return crudVoiteRepository.getAll(dateSet);
    }

    @Override
    @Transactional
    public boolean setVoite(int userId, int menuId) {
        Voite voite = crudVoiteRepository.get(userId, dateTimeUtil.getCurrentDate());

        // уже проголосовал в указанную дату
        if(voite != null){
            // если поменял выбор
            if(voite.getMenu().getId() != menuId) {
                Menu menu = crudMenuRepository.getOne(menuId);
                if(dateTimeUtil.enableVoite(menu.getDateSet())) {
                    voite.setMenu(crudMenuRepository.getOne(menuId));
                    crudVoiteRepository.save(voite);
                    return true;
                }
            }
            // если не поменял выбор, то просто возвращает доступность изменения
            else {
                if(dateTimeUtil.enableVoite(voite.getMenu().getDateSet())) {
                    return true;
                }
            }
        }
        // новое голосование в указанном числе
        else {
            Menu menu = crudMenuRepository.getOne(menuId);
            if(dateTimeUtil.enableVoite(menu.getDateSet())) {
                voite = new Voite(crudUserRepository.getOne(userId), crudMenuRepository.getOne(menuId), LocalDateTime.now());
                crudVoiteRepository.save(voite);
                return true;
            }
        }
        return false;
    }

    @Override
    public Voite get(int userId, LocalDate dateSet) {
        return crudVoiteRepository.get(userId, dateSet);
    }
}
