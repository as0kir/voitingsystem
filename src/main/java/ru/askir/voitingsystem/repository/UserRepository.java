package ru.askir.voitingsystem.repository;

import org.springframework.stereotype.Repository;
import ru.askir.voitingsystem.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    List<User> getAll();
}
