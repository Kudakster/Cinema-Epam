package com.epam.cinema.service;

import com.epam.cinema.dao.implementation.DAOScreeningImpl;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.service.implementation.ScreeningServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ScreeningServiceTest {
    @Mock
    private DAOScreeningImpl daoScreening;

    @InjectMocks
    private ScreeningServiceImpl screeningService;

    private Screening screening;

    @BeforeEach
    public void setup() {
        screening = new Screening();
                screening.setScreeningID(1);
                screening.setMovieID(1);
                screening.setDate(new Date(System.currentTimeMillis()));
                screening.setAuditoriumID(1);
                screening.setStartTime(new Time(System.currentTimeMillis()));
                screening.setEndTime(new Time(System.currentTimeMillis() + 3_600_000));
    }

    @DisplayName("JUnit test for findScreening method")
    @Test
    public void givenScreeningObject_whenFindScreeningByID_thenReturnScreeningObject() {
        given(daoScreening.getScreeningByID(screening.getScreeningID()))
                .willReturn(screening);

        Screening findScreening = screeningService.findScreeningById(screening.getScreeningID());
        assertThat(findScreening).isNotNull();
        assertThat(findScreening).isInstanceOf(Screening.class);
    }

    @DisplayName("JUnit test for findScreening method")
    @Test
    public void givenScreeningObject_whenFindScreeningByDateAndAuditoriumId_thenReturnScreeningObject() {
        given(daoScreening.getScreeningByDateAndAuditoriumID(screening.getDate(), screening.getAuditoriumID()))
                .willReturn(screening);

        Screening findScreening = screeningService.findScreeningByDateAndAuditoriumID(screening.getDate(), screening.getAuditoriumID());
        assertThat(findScreening).isNotNull();
        assertThat(findScreening).isInstanceOf(Screening.class);
    }

    @DisplayName("JUnit test for findAllScreening method")
    @Test
    public void givenScreeningObject_whenFindAllScreening_thenReturnListOfScreeningObject() {
        Screening anotherScreening = new Screening();
        screening.setScreeningID(2);
        screening.setMovieID(1);
        screening.setDate(new Date(System.currentTimeMillis()));
        screening.setAuditoriumID(1);
        screening.setStartTime(new Time(System.currentTimeMillis() - 3600000L * 4));

        given(daoScreening.getAllScreenings()).willReturn(List.of(screening, anotherScreening));

        List<Screening> screenings = screeningService.findAllScreenings();
        assertThat(screenings).isNotNull();
        assertThat(screenings.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllScreening method")
    @Test
    public void givenScreeningObject_whenFindAllScreeningByDate_thenReturnListOfScreeningObject() {
        Screening anotherScreening = new Screening();
        screening.setScreeningID(2);
        screening.setMovieID(1);
        screening.setDate(new Date(System.currentTimeMillis()));
        screening.setAuditoriumID(1);
        screening.setStartTime(new Time(System.currentTimeMillis() - 3600000L * 4));

        given(daoScreening.getScreeningsByDate(screening.getDate(), screening.getStartTime(),  "screening_start_time", "ASK"))
                .willReturn(List.of(screening, anotherScreening));

        List<Screening> screenings = screeningService.findScreeningsByDate(screening.getDate(), screening.getStartTime(), "screening_start_time", "ASK");
        assertThat(screenings).isNotNull();
        assertThat(screenings.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for saveScreening method")
    @Test
    public void givenScreeningObject_whenAddScreening_thenReturnBooleanObject() {
        given(daoScreening.addScreening(screening)).willReturn(true);

        boolean result = screeningService.addScreening(screening);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for updateScreening method")
    @Test
    public void givenScreeningObject_whenUpdateScreening_thenReturnBooleanObject() {
        given(daoScreening.updateScreening(screening)).willReturn(true);

        boolean result = screeningService.updateScreening(screening);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for deleteScreening method")
    @Test
    public void givenScreeningObject_whenDeleteScreening_thenVoid() {
        given(daoScreening.deleteScreeningByID(screening.getScreeningID())).willReturn(true);
        boolean result = screeningService.deleteScreeningByID(screening.getScreeningID());
        assertThat(result).isTrue();
    }
}
