package ua.nure.admin.summarytask.service.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.repository.UserRepository;
import ua.nure.admin.summarytask.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public String addUser(User user) {
        boolean flag = false;
        if (validateUser(user)) {
            flag = userRepository.addUser(user);
        }
        if (flag) {
            return "User added successfully";
        } else {
            return "Cannot add user";
        }
    }

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.checkUser(username, password);
        ;
        if (user.getUsername() == null) {
            return null;
        } else {
            return user;
        }
    }

    @Override
    public void updateUserPassword(User user) {
        userRepository.updateUserPassword(user);
    }

    @Override
    public void deleteUser(String username, String role) {
        userRepository.deleteUser(username, role);
    }

    @Override
    public User checkUser(String username) {
        return userRepository.checkUser(username);
    }


    private static boolean validateUser(User user) {
        if (user.getUsername() == null || user.getRole() == null || user.getEmail() == null) {
            log.error("Cannot validate user");
            return false;
        }
        return true;
    }
}
