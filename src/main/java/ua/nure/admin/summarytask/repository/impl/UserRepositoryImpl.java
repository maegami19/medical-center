package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.User;
import ua.nure.admin.summarytask.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    private final Logger log = Logger.getLogger(UserRepositoryImpl.class);

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.FIND_ALL_USERS);
            users = extractUsers(rs);
            log.info("Get users succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get users", e);
            e.printStackTrace();
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        Connection connection = getConnection();
        PreparedStatement prstmt = null;

        try {
            prstmt = connection.prepareStatement(DBConstant.ADD_USER);
            prstmt.setString(1, user.getUsername());
            prstmt.setString(2, user.getPassword());
            prstmt.setString(3, user.getRole());
            prstmt.setString(4, user.getEmail());
            prstmt.execute();
            log.info("User added succesfully.");
        } catch (SQLException e) {
            log.error("Cannot added user", e);
            e.printStackTrace();
        } finally {
            close(prstmt);
            close(connection);
        }
    }

    private List<User> extractUsers(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<User>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setEmail(rs.getString("email"));

            users.add(user);
        }
        return users;
    }
}
