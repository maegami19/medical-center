package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.Nurse;
import ua.nure.admin.summarytask.repository.NurseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class NurseRepositoryImpl extends AbstractRepository implements NurseRepository {

    private final Logger log = Logger.getLogger(NurseRepositoryImpl.class);

    @Override
    public boolean addNurse(Nurse nurse) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        boolean flag = false;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.ADD_NURSE);
            preparedStatement.setString(1, nurse.getFirstname());
            preparedStatement.setString(2, nurse.getLastname());
            preparedStatement.setString(3, nurse.getUsername());
            preparedStatement.execute();
            flag = true;
            log.info("Nurse added successfully.");
        } catch (SQLException e) {
            log.error("Cannot add nurse.", e);
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public int getNurseId(String username) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_NURSE_ID);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id");
            log.info("Get nurse id successfully");
        } catch (SQLException e) {
            log.error("Cannot get nurse id: ", e);
        } finally {
            close(preparedStatement);
            close(connection);
            close(resultSet);
        }
        return id;
    }

    @Override
    public String getNameById(int id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String result = "";

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_NURSE_NAME_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = result + resultSet.getString("firstname") + " " + resultSet.getString("lastname");
            }
            log.info("Get nurse name successfully");
        } catch (SQLException e) {
            log.error("Cannot get nurse name: ", e);
        } finally {
            close(preparedStatement);
            close(connection);
            close(resultSet);
        }
        return result;
    }
}
