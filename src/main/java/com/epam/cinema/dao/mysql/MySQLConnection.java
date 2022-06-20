package com.epam.cinema.dao.mysql;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


public class MySQLConnection {
    private static BasicDataSource basicDataSource;
    private static final Logger log = Logger.getLogger(MySQLConnection.class);

    public MySQLConnection() {
        basicDataSource = new BasicDataSource();
        MySQLConfig.setProperties(basicDataSource);
    }

    public Connection getConnection() {
        try {
            return basicDataSource.getConnection();
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new RuntimeException(sqle);
        }
    }
}
