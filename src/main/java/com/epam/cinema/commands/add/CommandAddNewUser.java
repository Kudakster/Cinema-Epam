package com.epam.cinema.commands.add;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.commands.RequestUtil;
import com.epam.cinema.commands.Validation;
import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class CommandAddNewUser implements ICommand {
    private RequestUtil requestUtil = new RequestUtil();
    private final String COMMAND = "login";
    private final String ERROR_COMMAND = "sign-up";
    private final String ERROR_KEY = "user.alreadyExist";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        User user = requestUtil.getUserFromRequest(request);
        String error = Validation.validate(user);

        if (!Objects.equals(error, "")) {
            return new Redirect(ERROR_COMMAND, error);
        }

        return new Redirect(ServiceFactory.getUserService().addUser(user), COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
