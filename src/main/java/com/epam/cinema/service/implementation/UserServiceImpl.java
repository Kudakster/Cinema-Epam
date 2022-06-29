package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOUserImpl;
import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;
import com.epam.cinema.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private DAOUserImpl daoUser;
    private static UserServiceImpl instance = null;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        daoUser = new DAOUserImpl();
    }

    @Override
    public User findUserById(Integer id) {
        return daoUser.getUserByID(id);
    }

    @Override
    public User findUserByLogin(String login) {
        return daoUser.getUserByLogin(login);
    }

    @Override
    public List<User> findAllUsers() {
        return daoUser.getAllUser();
    }

    @Override
    public List<User> findUsersByRole(UserRole userRole) {
        return daoUser.getUsersByRole(userRole);
    }

    @Override
    public User verifyUserAndReturnUser(User user) {
        User userDao = daoUser.getUserByLogin(user.getLogin());
        if (userDao != null && BCrypt.checkpw(user.getPassword(), userDao.getPassword())) {
            return userDao;
        } else {
            return null;
        }
    }

    @Override
    public boolean addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return daoUser.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return daoUser.updateUserByID(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return daoUser.deleteUser(user);
    }

    public static UserServiceImpl getInstance() {
        if (instance == null)
            instance = new UserServiceImpl();
        return instance;
    }

    public static void setInstance(UserServiceImpl instance) {
        UserServiceImpl.instance = instance;
    }
}
