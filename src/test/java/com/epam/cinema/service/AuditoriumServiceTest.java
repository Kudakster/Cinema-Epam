package com.epam.cinema.service;

import com.epam.cinema.dao.implementation.DAOAuditoriumImpl;
import com.epam.cinema.enity.Auditorium;
import com.epam.cinema.service.implementation.AuditoriumServiceImpl;
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
public class AuditoriumServiceTest {
    @Mock
    private DAOAuditoriumImpl daoAuditorium;

    @InjectMocks
    private AuditoriumServiceImpl auditoriumService;

    private Auditorium auditorium;

    @BeforeEach
    public void setup() {
        auditorium = new Auditorium();
        auditorium.setAuditoriumID(1);
        auditorium.setAuditoriumName("First");

    }

    @DisplayName("JUnit test for findAuditorium method")
    @Test
    public void givenAuditoriumObject_whenFindAuditoriumByID_thenReturnAuditoriumObject() {
        given(daoAuditorium.getAuditoriumById(auditorium.getAuditoriumID()))
                .willReturn(auditorium);

        Auditorium findAuditorium = auditoriumService.findAuditoriumById(auditorium.getAuditoriumID());
        assertThat(findAuditorium).isNotNull();
        assertThat(findAuditorium).isInstanceOf(Auditorium.class);
    }

    @DisplayName("JUnit test for findAuditorium method")
    @Test
    public void givenAuditoriumObject_whenFindAuditoriumByName_thenReturnAuditoriumObject() {
        given(daoAuditorium.getAuditoriumByName(auditorium.getAuditoriumName()))
                .willReturn(auditorium);

        Auditorium findAuditorium = auditoriumService.findAuditoriumByName(auditorium.getAuditoriumName());
        assertThat(findAuditorium).isNotNull();
        assertThat(findAuditorium).isInstanceOf(Auditorium.class);
    }

    @DisplayName("JUnit test for findAllAuditorium method")
    @Test
    public void givenAuditoriumObject_whenFindAllAuditorium_thenReturnListOfAuditoriumObject() {
        Auditorium anotherAuditorium = new Auditorium();
        anotherAuditorium.setAuditoriumID(1);
        anotherAuditorium.setAuditoriumName("Second");

        given(daoAuditorium.getAllAuditorium()).willReturn(List.of(auditorium, anotherAuditorium));

        List<Auditorium> auditoriums = auditoriumService.findAllAuditoriums();
        assertThat(auditoriums).isNotNull();
        assertThat(auditoriums.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for saveAuditorium method")
    @Test
    public void givenAuditoriumObject_whenAddAuditorium_thenReturnAuditoriumObject() {
        given(daoAuditorium.addAuditorium(auditorium)).willReturn(true);

        boolean result = auditoriumService.addAuditorium(auditorium);
        assertThat(result).isNotNull();
    }

    @DisplayName("JUnit test for updateAuditorium method")
    @Test
    public void givenAuditoriumObject_whenUpdateAuditorium_thenReturnUpdateAuditoriumObject() {
        auditorium.setAuditoriumName("Next Generation");

        given(daoAuditorium.updateAuditoriumByID(auditorium)).willReturn(true);

        boolean result = auditoriumService.updateAuditorium(auditorium);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for deleteAuditorium method")
    @Test
    public void givenAuditoriumObject_whenDeleteAuditorium_thenVoid() {
        given(daoAuditorium.deleteAuditorium(auditorium)).willReturn(true);
        boolean result = auditoriumService.deleteAuditorium(auditorium);
        assertThat(result).isTrue();
    }
}
