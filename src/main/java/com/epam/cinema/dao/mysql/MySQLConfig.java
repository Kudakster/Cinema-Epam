package com.epam.cinema.dao.mysql;

import org.apache.commons.dbcp.BasicDataSource;

import java.util.ResourceBundle;


public class MySQLConfig {
    private static String user = null;
    private static String password = null;
    private static String host = null;
    private static String port = null;
    private static String driver = null;
    private static String dataSource = null;
    private static String useUnicode = null;
    private static String encoding = null;
    private static String url = null;
    private static Integer minIdle = null;
    private static Integer maxIdle = null;
    private static Integer maxActive = null;
    private static Integer maxOpenPStatements = null;

    public static void setProperties(BasicDataSource basicDataSource) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("DB_Config");
        try {
            user = resourceBundle.getString("user");
            password = resourceBundle.getString("password");
            host = resourceBundle.getString("host");
            port = resourceBundle.getString("port");
            driver = resourceBundle.getString("driver");
            dataSource = resourceBundle.getString("database");
            useUnicode = resourceBundle.getString("useUnicode");
            encoding = resourceBundle.getString("encoding");
            url = "jdbc:mysql://" + host + ":" + port + "/" + MySQLConfig.dataSource + "?useUnicode=" + useUnicode + "&characterEncoding=" + encoding + "&useSSL=false";
            minIdle = Integer.parseInt(resourceBundle.getString("minIdle"));
            maxIdle = Integer.parseInt(resourceBundle.getString("maxIdle"));
            maxActive = Integer.parseInt(resourceBundle.getString("maxActive"));
            maxOpenPStatements = Integer.parseInt(resourceBundle.getString("maxOpenPreparedStatements"));
        } catch (NullPointerException | NumberFormatException npe) {
            npe.printStackTrace();
        }

        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(url);
        basicDataSource.setMinIdle(minIdle);
        basicDataSource.setMaxIdle(maxIdle);
        basicDataSource.setMaxActive(maxActive);
        basicDataSource.setMaxOpenPreparedStatements(maxOpenPStatements);
    }
}
