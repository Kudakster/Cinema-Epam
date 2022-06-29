package com.epam.cinema.service;

import com.epam.cinema.dao.implementation.DAOSeatReservedImpl;
import com.epam.cinema.enity.SeatReserved;
import com.epam.cinema.service.implementation.SeatReservedServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SeatReservedServiceTest {
    @Mock
    private DAOSeatReservedImpl daoSeatReserved;

    @InjectMocks
    private SeatReservedServiceImpl seatReservedService;

    private SeatReserved seatReserved;

    @BeforeEach
    public void setup() {
        seatReserved = new SeatReserved();
        seatReserved.setSeatID(1);
        seatReserved.setSeatReservedID(1);
        seatReserved.setScreeningID(1);
    }

    @DisplayName("JUnit test for findSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenFindSeatReservedByID_thenReturnSeatReservedObject() {
        given(daoSeatReserved.getSeatReservedByID(seatReserved.getSeatReservedID()))
                .willReturn(seatReserved);

        SeatReserved findSeatReserved = seatReservedService.findSeatReservedByID(seatReserved.getSeatReservedID());
        assertThat(findSeatReserved).isNotNull();
        assertThat(findSeatReserved).isInstanceOf(SeatReserved.class);
    }

    @DisplayName("JUnit test for findSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenFindSeatReservedByName_thenReturnSeatReservedObject() {
        given(daoSeatReserved.getSeatReservedBySeatIDAndScreeningID(seatReserved.getSeatID(), seatReserved.getScreeningID()))
                .willReturn(seatReserved);

        SeatReserved findSeatReserved = seatReservedService.findSeatReservedBySeatIDAndScreeningID(seatReserved.getSeatID(), seatReserved.getScreeningID());
        assertThat(findSeatReserved).isNotNull();
        assertThat(findSeatReserved).isInstanceOf(SeatReserved.class);
    }

    @DisplayName("JUnit test for findAllSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenFindAllSeatReserved_thenReturnListOfSeatReservedObject() {
        SeatReserved anotherSeatReserved = new SeatReserved();
        seatReserved.setSeatReservedID(2);
        seatReserved.setSeatID(2);
        seatReserved.setScreeningID(1);

        given(daoSeatReserved.getAllSeats()).willReturn(List.of(seatReserved, anotherSeatReserved));

        List<SeatReserved> seatReserved = seatReservedService.findAllSeatReserved();
        assertThat(seatReserved).isNotNull();
        assertThat(seatReserved.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenFindAllSeatReservedByScreeningId_thenReturnListOfSeatReservedObject() {
        SeatReserved anotherSeatReserved = new SeatReserved();
        seatReserved.setSeatReservedID(2);
        seatReserved.setSeatID(2);
        seatReserved.setScreeningID(1);

        given(daoSeatReserved.getAllSeatsByScreeningID(seatReserved.getScreeningID())).willReturn(List.of(seatReserved, anotherSeatReserved));

        List<SeatReserved> seatReservedList = seatReservedService.findAllSeatReservedByScreeningID(seatReserved.getScreeningID());
        assertThat(seatReservedList).isNotNull();
        assertThat(seatReservedList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for addSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenAddSeatReserved_thenReturnSeatReservedObject() {
        given(daoSeatReserved.addSeat(seatReserved)).willReturn(true);

        boolean result = seatReservedService.addSeatReserved(seatReserved);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for deleteSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenDeleteSeatReserved_thenVoid() {
        given(daoSeatReserved.deleteSeat(seatReserved)).willReturn(true);
        boolean result = seatReservedService.deleteSeatReserved(seatReserved);
        assertThat(result).isTrue();
    }
}
