package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.Medcard;
import ua.nure.admin.summarytask.repository.MedcardRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedcardRepositoryImpl extends AbstractRepository implements MedcardRepository {

    private final Logger log = Logger.getLogger(MedcardRepositoryImpl.class);

    @Override
    public void addNote(Medcard medcard) {
        Connection connection = getConnection();
        PreparedStatement prstmt = null;

        try {
            prstmt = connection.prepareStatement(DBConstant.ADD_NOTE_TO_MEDCARD);
            prstmt.setString(1, medcard.getFromMedic());
            prstmt.setInt(2, medcard.getToPatient());
            prstmt.setString(3, medcard.getType());
            prstmt.setString(4, medcard.getDescription());
            prstmt.setString(5, medcard.getStatus());
            prstmt.execute();
            log.info("Note to medcard added succesfully.");
        } catch (SQLException e) {
            log.error("Cannot add note to medcard: " + e);
        } finally {
            close(prstmt);
            close(connection);
        }
    }

    @Override
    public List<Medcard> getMedcardByDoctor(String username) {
        List<Medcard> medcards = new ArrayList<Medcard>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_MEDCARD_BY_DOCTOR + '\'' + username + '\'' + " AND status='active'");
            medcards = extractMedcards(rs);
            log.info("Medcards got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get medcards: " + e);
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return medcards;
    }

    @Override
    public void closeNote(Medcard medcard) {
        Connection connection = getConnection();
        PreparedStatement prstmt = null;

        try {
            prstmt = connection.prepareStatement(DBConstant.CLOSE_MEDCARD);
            prstmt.setString(1, medcard.getDiagnosis());
            prstmt.setInt(2, medcard.getId());
            prstmt.execute();
            log.info("Medcard closed succesfully");
        } catch (SQLException e) {
            log.error("Cannot close medcard: " + e);
        } finally {
            close(prstmt);
            close(connection);
        }
    }

    @Override
    public List<Medcard> getAllMedcards(int id) {
        List<Medcard> medcards = new ArrayList<Medcard>();
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_ALL_MEDCARDS + id);
            medcards = extractMedcards(rs);
            log.info("Medcards got succesfully.");
        } catch (SQLException e) {
            log.error("Cannot get medcards: " + e);
        } finally {
            close(rs);
            close(connection);
            close(stmt);
        }
        return medcards;
    }

    private List<Medcard> extractMedcards(ResultSet rs) throws SQLException {
        List<Medcard> medcards = new ArrayList<Medcard>();

        while (rs.next()) {
            Medcard medcard = new Medcard();

            medcard.setId(rs.getInt("id"));
            medcard.setFromMedic(rs.getString("from_medic"));
            medcard.setToPatient(rs.getInt("to_patient"));
            medcard.setType(rs.getString("type"));
            medcard.setDescription(rs.getString("description"));
            medcard.setStatus(rs.getString("status"));
            medcard.setDiagnosis(rs.getString("diagnosis"));

            medcards.add(medcard);
        }
        return medcards;
    }
}
