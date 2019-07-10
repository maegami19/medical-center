package ua.nure.admin.summarytask.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private final Logger log = Logger.getLogger(ConnectionPool.class);
    private int maxSize;
    private static ConnectionPool instance;
    private List<Connection> connectionPool;

    private ConnectionPool() {
        maxSize = 30;
        connectionPool = new ArrayList<>();
        log.info("Creating new instance of ConnectionPool with maxSize: " + maxSize);
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        PooledConnection pooledConnection = null;

        if (!connectionPool.isEmpty()) {
            connection = connectionPool.get(connectionPool.size() - 1);
            connectionPool.remove(connection);
        } else {
            connection = ConnectionManager.getInstance().getConnection();
        }

        pooledConnection = new PooledConnection(connection, getInstance());

        log.info("Getting new connection from pool");

        return pooledConnection;
    }

    public synchronized void freeConnection(Connection connection) {
        if ((connection != null) && (connectionPool.size() <= maxSize)) {
            connectionPool.add(connection);
            log.info("Connection was returned to the pool successfully");
        } else {
            if (connection != null) {
                try {
                    log.info("Connection closed. Size of the pool is larger than maxSize");
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close connection", e);
                    e.printStackTrace();
                }
            }
        }
    }
}
