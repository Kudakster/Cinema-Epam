package com.epam.cinema.commands;

import com.epam.cinema.commands.add.CommandAddMovie;
import com.epam.cinema.commands.add.CommandAddNewTicket;
import com.epam.cinema.commands.add.CommandAddNewUser;
import com.epam.cinema.commands.add.CommandAddScreening;
import com.epam.cinema.commands.delete.CommandDeleteMovie;
import com.epam.cinema.commands.delete.CommandDeleteScreening;
import com.epam.cinema.commands.open.*;
import com.epam.cinema.commands.update.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandResolver {
    private static CommandResolver instance = null;
    private final Map<String, ICommand> commands = new HashMap<>();

    public CommandResolver() {
        /* Commands available for All */
        commands.put("main", new CommandOpenMainPage());
        commands.put("screening", new CommandOpenScreeningPage());
        /* Commands available for Guest */
        commands.put("login", new CommandOpenLoginPage());
        commands.put("registration", new CommandOpenSignUpPage());
        commands.put("sign-in", new CommandValidateUser());
        commands.put("sign-up", new CommandAddNewUser());
        /* Commands available for User */
        commands.put("user", new CommandOpenUserPage());
        commands.put("buy-ticket", new CommandAddNewTicket());
        commands.put("update-user", new CommandUpdateUser());
        commands.put("logout", new CommandLogout());
        /* Commands available for Administration */
        commands.put("admin", new CommandOpenAdminPage());
        commands.put("movies", new CommandOpenListOfMoviesPage());
        commands.put("auditorium", new CommandOpenAuditoriumPage());
        commands.put("a-movie", new CommandOpenAddMoviePage());
        commands.put("u-movie", new CommandOpenUpdateMoviePage());
        commands.put("u-schedule", new CommandOpenUpdateSchedulePage());
        commands.put("statistic", new CommandOpenStatisticPage());
        commands.put("add-movie", new CommandAddMovie());
        commands.put("add-screening", new CommandAddScreening());
        commands.put("update-auditorium", new CommandUpdateAuditorium());
        commands.put("update-seats", new CommandUpdateSeats());
        commands.put("update-movie", new CommandUpdateMovie());
        commands.put("delete-movie", new CommandDeleteMovie());
        commands.put("delete-screening", new CommandDeleteScreening());
    }

    public ICommand getCommand(HttpServletRequest request) {
        return commands.get(getUri(request.getRequestURI()));
    }

    public static CommandResolver getInstance() {
        if (instance == null) {
            instance = new CommandResolver();
        }
        return instance;
    }

    private String getUri(String uri) {
        return uri.replace("/cinema/", "").replace("\\?.*", "");
    }
}
