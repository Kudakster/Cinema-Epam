package com.epam.cinema.servlets;

import com.epam.cinema.config.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirect implements Executor {
    private boolean isValid = true;
    private String command;
    private String errorCommand;
    private String errorKey;

    public Redirect(String command) {
        this.command = command;
    }

    public Redirect(boolean isValid, String command) {
        this.isValid = isValid;
        this.command = command;
    }

    public Redirect(String errorCommand, String errorKey) {
        this.isValid = false;
        this.errorCommand = errorCommand;
        this.errorKey = errorKey;
    }

    public Redirect(boolean isValid, String command, String errorCommand, String errorKey) {
        this.isValid = isValid;
        this.command = command;
        this.errorCommand = errorCommand;
        this.errorKey = errorKey;
    }

    @Override
    public void open(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isValid) {
            response.sendRedirect(command);
        } else {
            response.addCookie(new Cookie("error", Configuration.getInstance().getError(errorKey)));
            response.sendRedirect(errorCommand);
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public String getCommand() {
        return command;
    }

    public String getErrorCommand() {
        return errorCommand;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setErrorCommand(String errorCommand) {
        this.errorCommand = errorCommand;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }
}
