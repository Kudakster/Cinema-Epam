package com.epam.cinema.enity;

import com.epam.cinema.enity.enumeration.UserRole;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class User extends Entity {
    private Integer id;

    @Size(min = 4, max = 30,
    message = "user.size.login")
    private String login;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#^%*?&])[A-Za-z\\d@$!#^%*?&]{6,30}$",
            message = "user.pattern.password")
    @Size(min = 6, max = 30,
    message = "user.size.password")
    private String password;

    @Size(min = 2, max = 30,
            message = "user.size.firstName")
    private String firstName;

    @Size(min = 2, max = 30,
            message = "user.size.surName")
    private String surName;

    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$",
            message = "user.pattern.email")
    @Size(min = 5, max = 50,
            message = "user.size.email")
    private String email;

    @Pattern(regexp = "\\+380[0-9]{9}",
            message = "user.pattern.phoneNumber")
    @Size(min = 13, max = 13,
            message = "user.size.phoneNumber")
    private String phoneNumber;

    private UserRole userRole;

    public User(String login, String password, String firstName, String surName, String email, String phoneNumber, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
