package ua.nure.admin.summarytask.service;

import ua.nure.admin.summarytask.entity.User;

import java.util.List;

public interface UserService {

    /**
     * This method allows you to get a list of all users.
     *
     * @return list of users.
     */
    List<User> getAllUsers();

    /**
     * This method allows you to add a user to the database.
     *
     * @param user - user, who needs to be added.
     * @return string with result.
     */
    String addUser(User user);

    /**
     * This method allows you to check if the user is in the database.
     *
     * @param username - user's username.
     * @param password - user's password.
     * @return user, if exist.
     */
    User checkUser(String username, String password);

    /**
     * This method allows you to update the user password in the database.
     *
     * @param user - user, who needs to be updated.
     */
    void updateUserPassword(User user);

    /**
     * This method allows you to remove a user from the database.
     *
     * @param username - user's username.
     * @param role     - user's role.
     */
    void deleteUser(String username, String role);

    /**
     * This method allows you to check the presence of a user in the database by username.
     *
     * @param username - user's username.
     * @return user, if exist.
     */
    User checkUser(String username);
}
