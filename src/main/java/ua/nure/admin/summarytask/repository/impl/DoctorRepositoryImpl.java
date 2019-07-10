package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.Doctor;
import ua.nure.admin.summarytask.repository.DoctorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImpl extends AbstractRepository implements DoctorRepository {

    private final Logger log = Logger.getLogger(DoctorRepositoryImpl.class);

    @Override
    public void addDoctor(Doctor doctor) {
        Connection connection = getConnection();
        PreparedStatement prstmt = null;

        try {
            prstmt = connection.prepareStatement(DBConstant.ADD_DOCTOR);
            prstmt.setString(1, doctor.getFirstname());
            prstmt.setString(2, doctor.getLastname());
            prstmt.setString(3, doctor.getUsername());
            prstmt.setString(4, doctor.getCategory());
            prstmt.execute();
            log.info("Doctor added succesfully.");
        } catch (SQLException e) {
            log.error("Cannot add doctor: " + e);
        } finally {
            close(prstmt);
            close(connection);
        }
    }

    @Override
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_CATEGORIES);

            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
            log.info("Categories got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get categories: " + e);
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return categories;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.FIND_ALL_DOCTORS);
            doctors = extractDoctors(rs);
            log.info("Doctors got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get doctors: " + e);
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorsByAlphabet() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_DOCTORS_BY_ALPHABET);
            doctors = extractDoctors(rs);
            log.info("Doctors got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get doctors: " + e);
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorsByCategory() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_DOCTORS_BY_CATEGORY);
            doctors = extractDoctors(rs);
            log.info("Doctors got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get doctors: " + e);
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorsByCountOfPatient() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_DOCTORS_BY_COUNT_OF_PATIENT);
            doctors = extractDoctors(rs);
            log.info("Doctors got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get doctors: " + e);
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return doctors;
    }

    @Override
    public int getDoctorId(String username) {
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_DOCTOR_ID + '\'' + username + '\'');
            rs.next();
            id = rs.getInt("id");
            log.info("Get doctor id succesfully");
        } catch (SQLException e) {
            log.error("Cannot get doctor id: " + e);
        } finally {
            close(stmt);
            close(connection);
            close(rs);
        }
        return id;
    }

    private List<Doctor> extractDoctors(ResultSet rs) throws SQLException {
        List<Doctor> doctors = new ArrayList<Doctor>();

        while (rs.next()) {
            Doctor doctor = new Doctor();

            doctor.setId(rs.getInt("id"));
            doctor.setFirstname(rs.getString("firstname"));
            doctor.setLastname(rs.getString("lastname"));
            doctor.setCountPatient(rs.getInt("count_patient"));
            doctor.setCategory(rs.getString("category"));
            doctor.setUsername(rs.getString("username"));

            doctors.add(doctor);
        }
        return doctors;
    }
}
