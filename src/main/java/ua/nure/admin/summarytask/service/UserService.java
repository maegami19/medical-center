package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void addUser(User user);
}
