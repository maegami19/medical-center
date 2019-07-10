package ua.nure.admin.summarytask.repository;

import ua.nure.admin.summarytask.entity.User;
import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();
    void addUser(User user);
}
