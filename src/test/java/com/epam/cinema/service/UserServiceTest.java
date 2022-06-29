package com.epam.cinema.service;

import com.epam.cinema.dao.implementation.DAOUserImpl;
import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;
import com.epam.cinema.service.implementation.TicketServiceImpl;
import com.epam.cinema.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private DAOUserImpl daoUser;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setup() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        user = new User();
        user.setId(1);
        user.setLogin("user");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setFirstName("First");
        user.setPhoneNumber("+28013123");
        user.setUserRole(UserRole.USER);
    }

    @DisplayName("JUnit test for saveUser method")
    @Test
    public void givenUser_whenAddUser_thenReturnUser() {
        given(daoUser.addUser(user)).willReturn(true);

        boolean result = userService.addUser(user);
        assertThat(result).isNotNull();
    }

    @DisplayName("JUnit test for findUser method")
    @Test
    public void givenUser_whenFindUserByID_thenReturnUser() {
        given(daoUser.getUserByID(user.getId()))
                .willReturn(user);

        User findUser = userService.findUserById(user.getId());
        assertThat(findUser).isNotNull();
        assertThat(findUser).isInstanceOf(User.class);
    }

    @DisplayName("JUnit test for findUser method")
    @Test
    public void givenUser_whenFindUserByLogin_thenReturnUser() {
        given(daoUser.getUserByLogin(user.getLogin()))
                .willReturn(user);

        User findUser = userService.findUserByLogin(user.getLogin());
        assertThat(findUser).isNotNull();
        assertThat(findUser).isInstanceOf(User.class);
    }

    @DisplayName("JUnit test for findAllUser method")
    @Test
    public void givenUser_whenFindAllUser_thenReturnUser() {
        User anotherUser = new User();
        anotherUser.setId(2);
        anotherUser.setLogin("anotherUser");
        anotherUser.setPassword("password");
        anotherUser.setFirstName("Second");
        anotherUser.setPhoneNumber("+28013123");
        anotherUser.setUserRole(UserRole.USER);

        given(daoUser.getAllUser()).willReturn(List.of(user, anotherUser));

        List<User> users = userService.findAllUsers();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllUser method")
    @Test
    public void givenUser_whenFindAllUserByRole_thenReturnUser() {
        User anotherUser = new User();
        anotherUser.setId(2);
        anotherUser.setLogin("anotherUser");
        anotherUser.setPassword("password");
        anotherUser.setFirstName("Second");
        anotherUser.setPhoneNumber("+28013123");
        anotherUser.setUserRole(UserRole.ADMIN);

        given(daoUser.getUsersByRole(user.getUserRole())).willReturn(List.of(user));

        List<User> users = userService.findUsersByRole(user.getUserRole());
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for verifyUser method")
    @Test
    public void givenUser_whenVerifyUser_thenReturnBoolean() {
        given(daoUser.getUserByLogin(user.getLogin()))
                .willReturn(user);
        User userFromRequest = new User("user", "password");
        userFromRequest.setPassword("password");
        User verifyUser = userService.verifyUserAndReturnUser(userFromRequest);
        assertThat(verifyUser).isNotNull();
    }

    @DisplayName("JUnit test for verifyUser method")
    @Test
    public void givenUser_whenUserNotFound_thenReturnBoolean() {
        given(daoUser.getUserByLogin(user.getLogin()))
                .willReturn(null);
        User userFromRequest = new User("user", "password");
        userFromRequest.setPassword("password");
        User verifyUser = userService.verifyUserAndReturnUser(userFromRequest);
        assertThat(verifyUser).isNull();
    }

    @DisplayName("JUnit test for updateUser method")
    @Test
    public void givenUser_whenUpdateUser_thenReturnUpdateUser() {
        user.setLogin("updateUser");

        given(daoUser.updateUserByID(user)).willReturn(true);

        boolean result = userService.updateUser(user);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for deleteUser method")
    @Test
    public void givenUser_whenDeleteUser_thenVoid() {
        given(daoUser.deleteUser(user)).willReturn(true);
        boolean result = userService.deleteUser(user);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for deleteTicket method")
    @Test
    public void givenUserObject_whenGetInstance_thenUserServiceImpl() {
        UserServiceImpl service = ServiceFactory.getUserService();
        assertThat(service).isInstanceOf(UserServiceImpl.class);
    }
}
