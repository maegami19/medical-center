package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.Patient;
import ua.nure.admin.summarytask.repository.PatientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl extends AbstractRepository implements PatientRepository {

    private final Logger log = Logger.getLogger(PatientRepositoryImpl.class);

    @Override
    public boolean addPatient(Patient patient) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        boolean flag;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.ADD_PATIENT);
            preparedStatement.setString(1, patient.getFirstname());
            preparedStatement.setString(2, patient.getLastname());
            preparedStatement.setString(3, patient.getBirthday());
            preparedStatement.setString(4, patient.getUsername());
            preparedStatement.setString(5, patient.getDoctorId());
            preparedStatement.execute();
            log.info("Patient added successfully.");
            flag = true;
            preparedStatement = connection.prepareStatement(DBConstant.UPDATE_COUNT_PATIENT);
            preparedStatement.setString(1, patient.getDoctorId());
            preparedStatement.execute();
            log.info("Count of patient update successfully.");
            flag = true;
        } catch (SQLException e) {
            log.error("Cannot add patient: ", e);
            flag = false;
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public List<Patient> getPatientsByAlphabet() {
        List<Patient> patients = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.GET_PATIENTS_BY_ALPHABET);
            patients = extractPatients(resultSet);
            log.info("Patients got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get patients: ", e);
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(connection);
            close(statement);
        }
        return patients;
    }

    @Override
    public List<Patient> getPatientsByBirthday() {
        List<Patient> patients = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.GET_PATIENTS_BY_BIRTHDAY);
            patients = extractPatients(resultSet);
            log.info("Patients got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get patients: ", e);
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(connection);
            close(statement);
        }
        return patients;
    }

    @Override
    public List<Patient> getPatientByDoctorId(int id) {
        List<Patient> patients = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_PATIENTS_BY_DOCTOR_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            patients = extractPatients(resultSet);
            log.info("Patients got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get patients: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(preparedStatement);
        }
        return patients;
    }

    @Override
    public int getId(String username) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_PATIENT_ID);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id");
            log.info("Get patient id successfully");
        } catch (SQLException e) {
            log.error("Cannot get patient id: ", e);
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
            preparedStatement = connection.prepareStatement(DBConstant.GET_NAME_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = result + resultSet.getString("firstname") + " " + resultSet.getString("lastname");
            }
            log.info("Get patient name successfully");
        } catch (SQLException e) {
            log.error("Cannot get patient name: ", e);
        } finally {
            close(preparedStatement);
            close(connection);
            close(resultSet);
        }
        return result;
    }

    @Override
    public int getDoctorId(String username) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_DOCTOR_ID_BY_USERNAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("doctor_id");
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

    private List<Patient> extractPatients(ResultSet resultSet) throws SQLException {
        List<Patient> patients = new ArrayList<>();

        while (resultSet.next()) {
            Patient patient = new Patient();

            patient.setId(resultSet.getInt("id"));
            patient.setFirstname(resultSet.getString("firstname"));
            patient.setLastname(resultSet.getString("lastname"));
            patient.setBirthday(resultSet.getString("birthday"));
            patient.setDoctorId(resultSet.getString("doctor_id"));
            patient.setUsername(resultSet.getString("username"));

            patients.add(patient);
        }
        return patients;
    }
}
