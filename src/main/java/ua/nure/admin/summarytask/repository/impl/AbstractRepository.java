package ua.nure.admin.summarytask.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.admin.summarytask.db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public abstract class AbstractRepository {

    private ConnectionPool connectionPool;
    private final Logger log = Logger.getLogger(AbstractRepository.class);

    protected AbstractRepository() {
        this.connectionPool = ConnectionPool.getInstance();
    }

    protected Connection getConnection() {
        return connectionPool.getConnection();
    }

    protected void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Cannot rollback connection: " + e);
            e.printStackTrace();
        }
    }

    protected void close(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            log.error("Cannot close prepared statement: " + e);
            e.printStackTrace();
        }
    }

    protected void close(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            log.error("Cannot close statement: " + e);
            e.printStackTrace();
        }
    }

    protected void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            log.error("Cannot close result set: " + e);
            e.printStackTrace();
        }
    }
}
