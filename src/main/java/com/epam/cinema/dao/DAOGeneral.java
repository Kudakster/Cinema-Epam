package com.epam.cinema.dao;

import com.epam.cinema.dao.mysql.MySQLConnection;
import com.epam.cinema.dao.utils.Mapper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class DAOGeneral<T> {
    private final Supplier<T> supplier;
    private Mapper<T, PreparedStatement> statementMapper;
    private Mapper<ResultSet, T> objectMapper;
    private final MySQLConnection mySQLConnection = new MySQLConnection();

    private static final Logger log = Logger.getLogger(DAOGeneral.class);

    protected DAOGeneral(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    protected void setStatementMapper(Mapper<T, PreparedStatement> statementMapper) {
        this.statementMapper = statementMapper;
    }

    protected void setObjectMapper(Mapper<ResultSet, T> objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SafeVarargs
    protected final <V> T get(String SQL_STATEMENT, V... value) {
        T instance = supplier.get();

        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATEMENT)) {

            for (int i = 1; i <= value.length; i++) {
                preparedStatement.setObject(i, value[i - 1]);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    objectMapper.map(resultSet, instance);
                } else {
                    instance = null;
                }
            }
        } catch (SQLException sql) {
            instance = null;
            log.error(sql);
        }

        return instance;
    }

    protected List<T> getAll(String SQL_STATEMENT) {
        List<T> list = null;
        T instance = supplier.get();

        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATEMENT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                list = new ArrayList<>();
                do {
                    objectMapper.map(resultSet, instance);
                    list.add(instance);
                    instance = supplier.get();
                } while (resultSet.next());
            }
        } catch (SQLException sql) {
            list = null;
            log.error(sql);
        }
        return list;
    }

    protected <V> List<T> getAllBy(String SQL_STATEMENT, V... value) {
        List<T> list = null;
        T instance = supplier.get();

        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATEMENT)) {
            addValuesToPreparedStatement(preparedStatement, value);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    list = new ArrayList<>();
                    do {
                        objectMapper.map(resultSet, instance);
                        list.add(instance);
                        instance = supplier.get();
                    } while (resultSet.next());
                }
            }
        } catch (SQLException sql) {
            list = null;
            log.error(sql);
        }
        return list;
    }

    protected boolean add(T t, String SQL_STATEMENT) {
        boolean result;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATEMENT)) {
            statementMapper.map(t, preparedStatement);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
            log.error(sqle);
            return false;
        }
        return result;
    }

    protected boolean addMany(List<T> list, String SQL_STATEMENT) {
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATEMENT)) {

            list.forEach(t -> {
                try {
                    statementMapper.map(t, preparedStatement);
                    preparedStatement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            preparedStatement.executeBatch();
        } catch (SQLException sqle) {
            log.error(sqle);
            return false;
        }
        return true;
    }

    protected <V> boolean update(T t, String SQL_STATEMENT, Integer paramNum, V value) {
        boolean result;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATEMENT)) {
            statementMapper.map(t, preparedStatement);
            addParameterToPreparedStatement(preparedStatement, paramNum, value);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
            log.error(sqle);
            return false;
        }
        return result;
    }

    protected <V> boolean delete(V value, String SQL_STATEMENT) {
        boolean result;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATEMENT)) {
            addParameterToPreparedStatement(preparedStatement, 1, value);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
            log.error(sqle);
            return false;
        }
        return result;
    }

    protected <V> boolean deleteMany(List<V> value, String SQL_STATEMENT) {
        boolean result;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATEMENT)) {
            addParametersToPreparedStatement(preparedStatement, value);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
            log.error(sqle);
            return false;
        }
        return result;
    }

    protected <V> int count(String SQL_STATEMENT, V... value) {
        int result = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATEMENT)) {
            addValuesToPreparedStatement(preparedStatement, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException sqle) {
            log.error(sqle);
            return 0;
        }
        return result;
    }


    private <V> void addParameterToPreparedStatement(PreparedStatement preparedStatement, Integer paramNum, V value)
            throws SQLException {
        if (value instanceof String)
            preparedStatement.setString(paramNum, (String) value);
        if (value instanceof Integer)
            preparedStatement.setInt(paramNum, (Integer) value);
        if (value instanceof Long)
            preparedStatement.setLong(paramNum, (Long) value);
        if (value instanceof Date)
            preparedStatement.setDate(paramNum, (Date) value);
    }

    private <V> void addParametersToPreparedStatement(PreparedStatement preparedStatement, List<V> value)
            throws SQLException {
        AtomicInteger counter = new AtomicInteger(1);
        value.forEach((v) -> {
            try {
                if (v instanceof String)
                    preparedStatement.setString(counter.getAndIncrement(), (String) v);
                if (v instanceof Integer)
                    preparedStatement.setInt(counter.getAndIncrement(), (Integer) v);
                if (v instanceof Long)
                    preparedStatement.setLong(counter.getAndIncrement(), (Long) v);
                if (v instanceof Date)
                    preparedStatement.setString(counter.getAndIncrement(), v.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private <V> void addValuesToPreparedStatement(PreparedStatement preparedStatement, V[] value) throws SQLException {
        for (int i = 1; i <= value.length; i++) {
            preparedStatement.setObject(i, value[i - 1]);
        }
    }
}
