package ru.askir.voitingsystem.service;

import ru.askir.voitingsystem.model.User;
import ru.askir.voitingsystem.util.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    void update(User user);

    List<User> getAll();
}
