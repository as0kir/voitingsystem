package ru.askir.voitingsystem.service;

import ru.askir.voitingsystem.model.User;
import ru.askir.voitingsystem.to.UserTo;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);

    User create(UserTo userTo);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    List<User> getAll();

    User getByEmail(String email) throws NotFoundException;

    void enable(int id, boolean enable);
}
