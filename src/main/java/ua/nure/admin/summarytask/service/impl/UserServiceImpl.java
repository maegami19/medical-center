package ua.nure.admin.summarytask.service.impl;

import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.repository.UserRepository;
import ua.nure.admin.summarytask.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }
}
