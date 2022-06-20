package com.epam.cinema.service;

import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    List<User> findAllUsers();

    List<User> findUsersByRole(UserRole userRole);

    User findUserById(Integer id);

    User findUserByLogin(String login);

    User verifyUser(User user);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);
}
