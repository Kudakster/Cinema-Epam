package com.epam.cinema.commands.add;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.User;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandAddNewTicket implements ICommand {
    private final String COMMAND = "user";
    private final String ERROR_COMMAND = "main";
    private final String ERROR_KEY = "ticket.alreadyExist";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> map = request.getParameterMap();
        String[] ids = map.get("seatID");

        if (ids == null) {
            return new Redirect(ERROR_COMMAND, "");
        }

        User user = (User) request.getSession().getAttribute("user");
        Integer screeningID = (Integer) request.getSession().getAttribute("screening-id");

        List<Integer> seatID = Arrays.stream(ids)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return new Redirect(ServiceFactory.getTicketService().addTickets(seatID, screeningID, user.getId()),
                COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
