package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.entity.Nurse;
import ua.nure.admin.summarytask.repository.NurseRepository;

import java.sql.*;

public class NurseRepositoryImpl extends AbstractRepository implements NurseRepository {

    private final Logger log = Logger.getLogger(NurseRepositoryImpl.class);

    @Override
    public void addNurse(Nurse nurse) {
        Connection connection = getConnection();
        PreparedStatement prstmt = null;

        try {
            prstmt = connection.prepareStatement(DBConstant.ADD_NURSE);
            prstmt.setString(1, nurse.getFirstname());
            prstmt.setString(2, nurse.getLastname());
            prstmt.setString(3, nurse.getUsername());
            prstmt.execute();
            log.info("Nurse added succesfully.");
        } catch (SQLException e) {
            log.error("Cannot add nurse." + e);
            e.printStackTrace();
        } finally {
            close(prstmt);
            close(connection);
        }
    }

    @Override
    public int getNurseId(String username) {
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(DBConstant.GET_NURSE_ID + '\'' + username + '\'');
            rs.next();
            id = rs.getInt("id");
            log.info("Get nurse id succesfully");
        } catch (SQLException e) {
            log.error("Cannot get nurse id: " + e);
        } finally {
            close(stmt);
            close(connection);
            close(rs);
        }
        return id;
    }
}
