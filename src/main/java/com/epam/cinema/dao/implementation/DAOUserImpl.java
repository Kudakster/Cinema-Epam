package com.epam.cinema.dao.implementation;

import com.epam.cinema.dao.DAOGeneral;
import com.epam.cinema.dao.IDAOUser;
import com.epam.cinema.dao.mysql.MySQLConstants;
import com.epam.cinema.dao.utils.Mapper;
import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOUserImpl extends DAOGeneral<User> implements IDAOUser<User> {

    public DAOUserImpl() {
        super(User::new);

        Mapper<ResultSet, User> userMapper = (ResultSet resultSet, User user) -> {
            user.setId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setFirstName(resultSet.getString(4));
            user.setSurName(resultSet.getString(5));
            user.setEmail(resultSet.getString(6));
            user.setPhoneNumber(resultSet.getString(7));
            user.setUserRole(UserRole.valueOf(resultSet.getString(8)));
        };
        super.setObjectMapper(userMapper);

        Mapper<User, PreparedStatement> statementMapper = (User user, PreparedStatement preparedStatement) -> {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getSurName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setString(7, user.getUserRole().toString());
        };
        super.setStatementMapper(statementMapper);
    }

    @Override
    public List<User> getUsersByRole(UserRole userRole) {
        return getAllBy(userRole.toString(), MySQLConstants.User.SQL_GET_USER_BY_ROLE);
    }

    @Override
    public User getUserByID(Integer id) {
        return get(MySQLConstants.User.SQL_GET_USER_BY_ID, id);
    }

    @Override
    public User getUserByLogin(String login) {
        return get(MySQLConstants.User.SQL_GET_USER_BY_LOGIN, login);
    }

    @Override
    public List<User> getAllUser() {
        return getAll(MySQLConstants.User.SQL_GET_ALL_USERS);
    }

    @Override
    public boolean addUser(User user) {
        return add(user, MySQLConstants.User.SQL_ADD_USER);
    }

    @Override
    public boolean updateUserByID(User user) {
        return update(user, MySQLConstants.User.SQL_UPDATE_USER_BY_ID, 8, user.getId());
    }

    @Override
    public boolean deleteUser(User user) {
        return delete(user.getLogin(), MySQLConstants.User.SQL_DELETE_USER_BY_LOGIN);
    }
}
