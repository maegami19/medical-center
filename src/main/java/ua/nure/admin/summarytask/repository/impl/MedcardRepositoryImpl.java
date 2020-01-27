package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.repository.MedcardRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedcardRepositoryImpl extends AbstractRepository implements MedcardRepository {

    private final Logger log = Logger.getLogger(MedcardRepositoryImpl.class);

    @Override
    public boolean addNote(Medcard medcard) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        boolean flag = false;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.ADD_NOTE_TO_MEDCARD);
            preparedStatement.setString(1, medcard.getFromMedic());
            preparedStatement.setInt(2, medcard.getToPatient());
            preparedStatement.setString(3, medcard.getType());
            preparedStatement.setString(4, medcard.getDescription());
            preparedStatement.setString(5, medcard.getStatus());
            preparedStatement.execute();
            flag = true;
            log.info("Note to medcard added successfully.");
        } catch (SQLException e) {
            log.error("Cannot add note to medcard: ", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public List<Medcard> getMedcardByDoctor(String username) {
        List<Medcard> medcards = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_MEDCARD_BY_DOCTOR);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            medcards = extractMedcards(resultSet);
            log.info("Medcards got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get medcards: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(preparedStatement);
        }
        return medcards;
    }

    @Override
    public List<Medcard> getMedcardByPatient(int id) {
        List<Medcard> medcards = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_MEDCARD_BY_PATIENT);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            medcards = extractMedcards(resultSet);
            log.info("Medcards got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get medcards: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(preparedStatement);
        }
        return medcards;
    }

    @Override
    public boolean closeNote(Medcard medcard) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        boolean flag = false;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.CLOSE_MEDCARD);
            preparedStatement.setString(1, medcard.getDiagnosis());
            preparedStatement.setInt(2, medcard.getId());
            preparedStatement.execute();
            flag = true;
            log.info("Medcard closed successfully");
        } catch (SQLException e) {
            log.error("Cannot close medcard: ", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public List<Medcard> getAllMedcards(int id) {
        List<Medcard> medcards = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_ALL_MEDCARDS);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            medcards = extractMedcards(resultSet);
            log.info("Medcards got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get medcards: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(preparedStatement);
        }
        return medcards;
    }

    @Override
    public List<Medcard> getMedcardsById(int id) {
        List<Medcard> medcards = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(DBConstant.GET_MEDCARD_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            medcards = extractMedcards(resultSet);
            log.info("Medcard got successfully.");
        } catch (SQLException e) {
            log.error("Cannot get medcard: ", e);
        } finally {
            close(resultSet);
            close(connection);
            close(preparedStatement);
        }
        return medcards;
    }

    private List<Medcard> extractMedcards(ResultSet resultSet) throws SQLException {
        List<Medcard> medcards = new ArrayList<>();

        while (resultSet.next()) {
            Medcard medcard = new Medcard();

            medcard.setId(resultSet.getInt("id"));
            medcard.setFromMedic(resultSet.getString("from_medic"));
            medcard.setToPatient(resultSet.getInt("to_patient"));
            medcard.setType(resultSet.getString("type"));
            medcard.setDescription(resultSet.getString("description"));
            medcard.setStatus(resultSet.getString("status"));
            medcard.setDiagnosis(resultSet.getString("diagnosis"));

            medcards.add(medcard);
        }
        return medcards;
    }
}
