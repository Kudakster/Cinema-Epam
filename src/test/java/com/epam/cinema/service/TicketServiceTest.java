package com.epam.cinema.service;

import com.epam.cinema.dao.implementation.DAOTicketImpl;
import com.epam.cinema.enity.Ticket;
import com.epam.cinema.service.implementation.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private DAOTicketImpl daoTicket;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;

    @BeforeEach
    public void setup() {
        ticket = new Ticket();
        ticket.setTicketID(1);
        ticket.setUserID(1);
        ticket.setSearReservedID(1);
    }

    @DisplayName("JUnit test for findTicket method")
    @Test
    public void givenTicketObject_whenFindTicketByID_thenReturnTicketObject() {
        given(daoTicket.getTicketByID(ticket.getTicketID()))
                .willReturn(ticket);

        Ticket findTicket = ticketService.findTicketByID(ticket.getTicketID());
        assertThat(findTicket).isNotNull();
        assertThat(findTicket).isInstanceOf(Ticket.class);
    }

    @DisplayName("JUnit test for findAllTicket method")
    @Test
    public void givenTicketObject_whenFindAllTicket_thenReturnTicketObject() {
        Ticket anotherTicket = new Ticket();
        anotherTicket.setTicketID(2);
        anotherTicket.setUserID(1);
        anotherTicket.setSearReservedID(1);

        given(daoTicket.getAllTickets()).willReturn(List.of(ticket, anotherTicket));

        List<Ticket> tickets = ticketService.findAllTickets();
        assertThat(tickets).isNotNull();
        assertThat(tickets.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllTicket method")
    @Test
    public void givenTicketObject_whenFindAllTicketByUserId_thenReturnListOfTicketObject() {
        Ticket anotherTicket = new Ticket();
        anotherTicket.setTicketID(2);
        anotherTicket.setUserID(1);
        anotherTicket.setSearReservedID(1);

        given(daoTicket.getAllTicketByUserID(ticket.getUserID())).willReturn(List.of(ticket, anotherTicket));

        List<Ticket> tickets = ticketService.findTicketsByUserID(ticket.getUserID());
        assertThat(tickets).isNotNull();
        assertThat(tickets.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllTicket method")
    @Test
    public void givenTicketObject_whenFindAllTicketByUserIdAndCurrentTime_thenReturnListOfTicketObject() {
        Ticket anotherTicket = new Ticket();
        anotherTicket.setTicketID(2);
        anotherTicket.setUserID(1);
        anotherTicket.setSearReservedID(1);

        given(daoTicket.getAllTicketByUserIDAndCurrentTime(eq(ticket.getUserID()), any())).willReturn(List.of(ticket, anotherTicket));

        List<Ticket> tickets = ticketService.findTicketsByUserIDAndCurrentTime(ticket.getUserID());
        assertThat(tickets).isNotNull();
        assertThat(tickets.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for saveTicket method")
    @Test
    public void givenTicketObject_whenAddTicket_thenReturnTicketObject() {
        given(daoTicket.addTicket(ticket)).willReturn(true);

        boolean result = ticketService.addTicket(ticket);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for deleteTicket method")
    @Test
    public void givenTicketObject_whenDeleteTicket_thenVoid() {
        given(daoTicket.deleteTicket(ticket)).willReturn(true);
        boolean result = ticketService.deleteTicket(ticket);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for deleteTicket method")
    @Test
    public void givenTicketObject_whenGetInstance_thenTicketServiceImpl() {
        TicketServiceImpl service = ServiceFactory.getTicketService();
        assertThat(service).isInstanceOf(TicketServiceImpl.class);
    }
}
