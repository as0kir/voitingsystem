package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.askir.voitingsystem.model.User;
import ru.askir.voitingsystem.repository.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudUserRepository crudUserRepository;

    public User save(User user) {
        return crudUserRepository.save(user);
    }

    public boolean delete(int id) {
        return crudUserRepository.delete(id)!=0;
    }

    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME);
    }
}
