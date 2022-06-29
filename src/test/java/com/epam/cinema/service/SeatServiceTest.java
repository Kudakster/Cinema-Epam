package com.epam.cinema.service;

import com.epam.cinema.dao.implementation.DAOSeatImpl;
import com.epam.cinema.enity.Seat;
import com.epam.cinema.enity.SeatReserved;
import com.epam.cinema.service.implementation.SeatReservedServiceImpl;
import com.epam.cinema.service.implementation.SeatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SeatServiceTest {
    @Mock
    private SeatReservedServiceImpl seatReservedService;

    @Mock
    private DAOSeatImpl daoSeat;

    @InjectMocks
    private SeatServiceImpl seatService;

    private Seat seat;

    @BeforeEach
    public void setup() {
        seat = new Seat();
        seat.setSeatID(1);
        seat.setAuditoriumID(1);
        seat.setSeatNumber(1);
        seat.setSeatRow(1);
    }

    @DisplayName("JUnit test for findSeat method")
    @Test
    public void givenSeatObject_whenFindSeatByID_thenReturnSeatObject() {
        given(daoSeat.getSeatByID(seat.getSeatID()))
                .willReturn(seat);

        Seat findSeat = seatService.findSeatByID(seat.getSeatID());
        assertThat(findSeat).isNotNull();
        assertThat(findSeat).isInstanceOf(Seat.class);
    }

    @DisplayName("JUnit test for findSeat method")
    @Test
    public void givenSeatObject_whenFindSeatByTicketID_thenReturnSeatObject() {
        int ticketId = 1;

        given(daoSeat.getSeatByTicketID(ticketId))
                .willReturn(seat);

        Seat findSeat = seatService.findSeatByTicketID(ticketId);
        assertThat(findSeat).isNotNull();
        assertThat(findSeat).isInstanceOf(Seat.class);
    }

    @DisplayName("JUnit test for findAllSeat method")
    @Test
    public void givenSeatObject_whenFindAllSeat_thenReturnListOfSeatObject() {
        Seat anotherSeat = new Seat();
        seat.setSeatID(2);
        seat.setAuditoriumID(1);
        seat.setSeatNumber(2);
        seat.setSeatRow(1);

        given(daoSeat.getAllSeats()).willReturn(List.of(seat, anotherSeat));

        List<Seat> seat = seatService.findAllSeats();
        assertThat(seat).isNotNull();
        assertThat(seat.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllSeat method")
    @Test
    public void givenSeatObject_whenFindAllSeatByAuditoriumId_thenReturnListOfSeatObject() {
        Seat anotherSeat = new Seat();
        seat.setSeatID(2);
        seat.setAuditoriumID(1);
        seat.setSeatNumber(2);
        seat.setSeatRow(1);

        given(daoSeat.getAllSeatsByAuditoriumID(seat.getAuditoriumID())).willReturn(List.of(seat, anotherSeat));

        List<Seat> seatList = seatService.findAllSeatsByAuditoriumID(seat.getAuditoriumID());
        assertThat(seatList).isNotNull();
        assertThat(seatList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllSeat method")
    @Test
    public void givenSeatObject_whenFindAllAvailableSeatsByAuditoriumIdAndScreeningId_thenReturnListOfSeatObject() {
        Integer screeningId = 1;
        Seat anotherSeat = new Seat();
        anotherSeat.setSeatID(2);
        anotherSeat.setAuditoriumID(1);
        anotherSeat.setSeatNumber(1);
        anotherSeat.setSeatRow(1);


        SeatReserved seatReserved = new SeatReserved();
        seatReserved.setSeatID(2);

        List<Seat> seats = new ArrayList<>();
        seats.add(seat);
        seats.add(anotherSeat);

        given(daoSeat.getAllSeatsByAuditoriumID(seat.getAuditoriumID())).willReturn(seats);

        Map<Seat, Boolean> seatList = seatService.getSeatAvailableMap(screeningId);
        assertThat(seatList).isNotNull();
        assertThat(seatList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllRowAndSeat method")
    @Test
    public void givenSeatObject_whenFindAllRowsAndSeats_thenReturnMapOfIntegerAndLongObject() {
        Seat anotherSeat = new Seat();
        anotherSeat.setSeatID(2);
        anotherSeat.setAuditoriumID(1);
        anotherSeat.setSeatNumber(2);
        anotherSeat.setSeatRow(1);

        given(daoSeat.getAllSeats()).willReturn(List.of(seat, anotherSeat));

        Map<Integer, Long> auditoriumMap = seatService.findAllRowsAndSeats();
        assertThat(auditoriumMap).isNotNull();
        assertThat(auditoriumMap.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for findAllRowAndSeat method")
    @Test
    public void givenSeatObject_whenFindAllRowsAndSeats_thenReturnNull() {
        given(daoSeat.getAllSeats()).willReturn(null);

        Map<Integer, Long> auditoriumMap = seatService.findAllRowsAndSeats();
        assertThat(auditoriumMap).isNull();
    }

    @DisplayName("JUnit test for findAllRowAndSeat method")
    @Test
    public void givenSeatObject_whenFindAllRowsAndSeats_thenReturnEmptyMap() {
        given(daoSeat.getAllSeats()).willReturn(List.of());

        Map<Integer, Long> auditoriumMap = seatService.findAllRowsAndSeats();
        assertThat(auditoriumMap).isEmpty();
    }

    @DisplayName("JUnit test for addSeat method")
    @Test
    public void givenSeatObject_whenAddSeat_thenReturnSeatObject() {
        given(daoSeat.addSeat(seat)).willReturn(true);

        boolean result = seatService.addSeat(seat);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for deleteSeat method")
    @Test
    public void givenSeatObject_whenDeleteSeat_thenVoid() {
        given(daoSeat.deleteSeat(seat)).willReturn(true);
        boolean result = seatService.deleteSeat(seat);
        assertThat(result).isTrue();
    }
}
