package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.Doctor;
import ua.nure.admin.summarytask.repository.DoctorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImpl extends AbstractRepository implements DoctorRepository {

    private final Logger log = Logger.getLogger(DoctorRepositoryImpl.class);

    @Override
    public boolean addDoctor(Doctor doctor) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        boolean flag = false;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.ADD_DOCTOR);
            preparedStatement.setString(1, doctor.getFirstname());
            preparedStatement.setString(2, doctor.getLastname());
            preparedStatement.setString(3, doctor.getUsername());
            preparedStatement.setString(4, doctor.getCategory());
            preparedStatement.execute();
            flag = true;
            log.info("Doctor added successfully.");
        } catch (SQLException e) {
            log.error("Cannot add doctor: ", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.GET_CATEGORIES);

            while (resultSet.next()) {
                categories.add(resultSet.getString("category"));
            }
            log.info("Categories got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get categories: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(statement);
        }
        return categories;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.FIND_ALL_DOCTORS);
            doctors = extractDoctors(resultSet);
            log.info("Doctors got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get doctors: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(statement);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorsByAlphabet() {
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.GET_DOCTORS_BY_ALPHABET);
            doctors = extractDoctors(resultSet);
            log.info("Doctors got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get doctors: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(statement);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorsByCategory() {
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.GET_DOCTORS_BY_CATEGORY);
            doctors = extractDoctors(resultSet);
            log.info("Doctors got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get doctors: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(statement);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorsByCountOfPatient() {
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.GET_DOCTORS_BY_COUNT_OF_PATIENT);
            doctors = extractDoctors(resultSet);
            log.info("Doctors got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get doctors: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(statement);
        }
        return doctors;
    }

    @Override
    public int getDoctorId(String username) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_DOCTOR_ID);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            log.info("Get doctor id successfully");
        } catch (SQLException e) {
            log.error("Cannot get doctor id: ", e);
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
        if (id < 0) {
            return "";
        }
        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_DOCTOR_NAME_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = result + resultSet.getString("firstname") + " " + resultSet.getString("lastname");
            }
            log.info("Get doctor name successfully");
        } catch (SQLException e) {
            log.error("Cannot get doctor name: ", e);
        } finally {
            close(preparedStatement);
            close(connection);
            close(resultSet);
        }
        return result;
    }

    private List<Doctor> extractDoctors(ResultSet resultSet) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();

        while (resultSet.next()) {
            Doctor doctor = new Doctor();

            doctor.setId(resultSet.getInt("id"));
            doctor.setFirstname(resultSet.getString("firstname"));
            doctor.setLastname(resultSet.getString("lastname"));
            doctor.setCountPatient(resultSet.getInt("count_patient"));
            doctor.setCategory(resultSet.getString("category"));
            doctor.setUsername(resultSet.getString("username"));

            doctors.add(doctor);
        }
        return doctors;
    }
}
