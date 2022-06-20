package com.epam.cinema.commands.add;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.Seat;
import com.epam.cinema.enity.SeatReserved;
import com.epam.cinema.enity.Ticket;
import com.epam.cinema.enity.User;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandAddNewTicket implements ICommand {
    private final String COMMAND = "user";
    private final String ERROR_COMMAND = "main";
    private final String ERROR_KEY = "ticket.alreadyExist";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        Integer screeningID = (Integer) request.getSession().getAttribute("screening-id");
        String[] ids = request.getParameterMap().get("seatID");
        User user = (User) request.getSession().getAttribute("user");
        Integer userID = user.getId();

        if (ids != null) {
            List<Integer> seatID = Arrays.stream(ids)
                    .flatMapToInt(s -> IntStream.of(Integer.parseInt(s)))
                    .boxed()
                    .collect(Collectors.toList());

            if (seatID.stream().noneMatch(id -> ServiceFactory.getSeatReservedService().isSeatReservedExist(id, screeningID))) {
                seatID.forEach(id -> ServiceFactory.getSeatReservedService().addSeatReserved(new SeatReserved(id, screeningID)));

                List<SeatReserved> seatReserved = seatID.stream()
                        .map(id -> ServiceFactory.getSeatReservedService()
                                .findSeatReservedBySeatIDAndScreeningID(id, screeningID))
                        .collect(Collectors.toList());

                seatReserved.stream()
                        .map(SeatReserved::getSeatReservedID)
                        .forEach(id -> {
                    ServiceFactory.getTicketService().addTicket(new Ticket(userID, id));
                });
            } else {
                return new Redirect(false, COMMAND, ERROR_COMMAND, ERROR_KEY);
            }
        }

       return new Redirect(true, COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
