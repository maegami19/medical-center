package ua.nure.admin.summarytask.db;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.constant.DBConstant;
import ua.nure.admin.summarytask.db.exception.ConnectionManagerException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private final Logger log = Logger.getLogger(ConnectionManager.class);
    private DataSource dataSource;
    private static ConnectionManager instance;

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    private ConnectionManager() {
        InitialContext context;
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup(DBConstant.DB_URL);
            log.info("Complete creating new instance");
        } catch (NamingException e) {
            log.error(e);
            throw new ConnectionManagerException(e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error(e);
            throw new ConnectionManagerException(e.getMessage());
        }
    }
}