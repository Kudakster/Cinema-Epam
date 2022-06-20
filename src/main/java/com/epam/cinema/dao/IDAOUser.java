package com.epam.cinema.dao;

import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;

import java.util.List;

public interface IDAOUser<User> {
    List<User> getUsersByRole(UserRole userRole);

    User getUserByID(Integer id);

    User getUserByLogin(String login);

    List<User> getAllUser();

    boolean addUser(User t);

    boolean updateUserByID(User t);

    boolean deleteUser(User t);
}
