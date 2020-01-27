package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    private final Logger log = Logger.getLogger(UserRepositoryImpl.class);

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.FIND_ALL_USERS);
            users = extractUsers(resultSet);
            log.info("Get users successfully.");
        } catch (SQLException e) {
            log.error("Cannot get users", e);
        } finally {
            close(resultSet);
            close(connection);
            close(statement);
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        boolean flag = false;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.ADD_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.execute();
            log.info("User added successfully.");
            flag = true;
        } catch (SQLException e) {
            log.error("Cannot added user");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public User checkUser(String username, String password) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = new User();

        try {
            preparedStatement = connection.prepareStatement(DBConstant.CHECK_USER);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            user = extractUser(resultSet);
            log.info("Check user successfully.");
        } catch (SQLException e) {
            log.error("Cannot check user", e);
        } finally {
            close(preparedStatement);
            close(connection);
            close(resultSet);
        }
        return user;
    }

    @Override
    public void updateUserPassword(User user) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.UPDATE_USER_PASSWORD);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.execute();
            log.info("User updated successfully.");
        } catch (SQLException e) {
            log.error("Cannot update user", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public void deleteUser(String username, String role) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.DELETE_USER);
            preparedStatement.setString(1, username);
            preparedStatement.execute();
            if ("patient".equals(role)) {
                preparedStatement = connection.prepareStatement(DBConstant.DELETE_PATIENT);
                preparedStatement.setString(1, username);
                preparedStatement.execute();
                preparedStatement = connection.prepareStatement(DBConstant.UPDATE_COUNT);
                preparedStatement.setString(1, username);
                preparedStatement.execute();
            } else if ("doctor".equals(role)) {
                preparedStatement = connection.prepareStatement(DBConstant.DELETE_DOCTOR);
                preparedStatement.setString(1, username);
                preparedStatement.execute();
            } else if ("nurse".equals(role)) {
                preparedStatement = connection.prepareStatement(DBConstant.DELETE_NURSE);
                preparedStatement.setString(1, username);
                preparedStatement.execute();
            }
            log.info("User deleted successfully.");
        } catch (SQLException e) {
            log.error("Cannot delete user", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public User checkUser(String username) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = new User();

        try {
            preparedStatement = connection.prepareStatement(DBConstant.CHECK_USER_BY_USERNAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            user = extractUser(resultSet);
            log.info("Check user successfully.");
        } catch (SQLException e) {
            log.error("Cannot check user", e);
        } finally {
            close(preparedStatement);
            close(connection);
            close(resultSet);
        }
        return user;
    }

    private List<User> extractUsers(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();

            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            user.setEmail(resultSet.getString("email"));

            users.add(user);
        }
        return users;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
        }

        return user;
    }
}
