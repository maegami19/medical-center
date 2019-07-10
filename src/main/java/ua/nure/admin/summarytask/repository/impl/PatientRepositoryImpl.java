package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.Patient;
import ua.nure.admin.summarytask.repository.PatientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl extends AbstractRepository implements PatientRepository {

    private final Logger log = Logger.getLogger(PatientRepositoryImpl.class);

    @Override
    public void addPatient(Patient patient) {
        Connection connection = getConnection();
        PreparedStatement prstmt = null;

        try {
            prstmt = connection.prepareStatement(DBConstant.ADD_PATIENT);
            prstmt.setString(1, patient.getFirstname());
            prstmt.setString(2, patient.getLastname());
            prstmt.setString(3, patient.getBirthday());
            prstmt.setString(4, patient.getUsername());
            prstmt.setString(5, patient.getDoctorId());
            prstmt.execute();
            log.info("Patient added succesfully.");
            prstmt = connection.prepareStatement(DBConstant.UPDATE_COUNT_PATIENT);
            prstmt.setString(1, patient.getDoctorId());
            prstmt.execute();
            log.info("Count of patient update succesfully.");
        } catch (SQLException e) {
            log.error("Cannot add patient: " + e);
            e.printStackTrace();
        } finally {
            close(prstmt);
            close(connection);
        }
    }

    @Override
    public List<Patient> getPatientsByAlphabet() {
        List<Patient> patients = new ArrayList<Patient>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_PATIENTS_BY_ALPHABET);
            patients = extractPatients(rs);
            log.info("Patients got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get patients: " + e);
            e.printStackTrace();
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return patients;
    }

    @Override
    public List<Patient> getPatientsByBirthday() {
        List<Patient> patients = new ArrayList<Patient>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_PATIENTS_BY_BIRTHDAY);
            patients = extractPatients(rs);
            log.info("Patients got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get patients: " + e);
            e.printStackTrace();
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return patients;
    }

    @Override
    public List<Patient> getPatientByDoctorId(int id) {
        List<Patient> patients = new ArrayList<Patient>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_PATIENTS_BY_DOCTOR_ID + id);
            patients = extractPatients(rs);
            log.info("Patients got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get patients: " + e);
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return patients;
    }

    @Override
    public int getId(String username) {
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_PATIENT_ID + '\'' + username + '\'');
            rs.next();
            id = rs.getInt("id");
            log.info("Get patient id succesfully");
        } catch (SQLException e) {
            log.error("Cannot get patient id: " + e);
        } finally {
            close(stmt);
            close(connection);
            close(rs);
        }
        return id;
    }

    private List<Patient> extractPatients(ResultSet rs) throws SQLException {
        List<Patient> patients = new ArrayList<Patient>();

        while (rs.next()) {
            Patient patient = new Patient();

            patient.setId(rs.getInt("id"));
            patient.setFirstname(rs.getString("firstname"));
            patient.setLastname(rs.getString("lastname"));
            patient.setBirthday(rs.getString("birthday"));
            patient.setDoctorId(rs.getString("doctor_id"));
            patient.setUsername(rs.getString("username"));

            patients.add(patient);
        }
        return patients;
    }
}
