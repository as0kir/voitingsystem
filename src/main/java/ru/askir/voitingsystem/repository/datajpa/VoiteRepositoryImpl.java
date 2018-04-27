package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.repository.VoiteRepository;

import java.util.List;

@Repository
public class VoiteRepositoryImpl implements VoiteRepository{
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "dateSet");

    @Autowired
    CrudVoiteRepository crudVoiteRepository;

    @Autowired
    CrudMenuRepository crudMenuRepository;

    @Autowired
    CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Voite save(Voite voite, int userId, int menuId) {
        if (!voite.isNew() && get(voite.getId(), userId, menuId) == null) {
            return null;
        }
        voite.setMenu(crudMenuRepository.getOne(menuId));
        voite.setUser(crudUserRepository.getOne(userId));
        return crudVoiteRepository.save(voite);
    }

    @Override
    public boolean delete(int id, int userId, int menuId) {
        return crudVoiteRepository.delete(id, userId, menuId) != 0;
    }

    @Override
    public Voite get(int id, int userId, int menuId) {
        return crudVoiteRepository.findById(id).filter(voite -> voite.getMenu().getId() == menuId && voite.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Voite> getAll(int userId, int menuId) {
        return crudVoiteRepository.getAll(userId, menuId);
    }

    @Override
    public Voite getWithUserAndMenu(int id, int userId, int menuId) {
        return crudVoiteRepository.getWithUserAndMenu(id, userId, menuId);
    }
    
}
