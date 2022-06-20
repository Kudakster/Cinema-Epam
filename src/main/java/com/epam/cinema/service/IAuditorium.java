package com.epam.cinema.service;

import com.epam.cinema.enity.Auditorium;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAuditorium {
    Auditorium findAuditoriumById(Integer id);

    Auditorium findAuditoriumByName(String name);

    Auditorium findAuditoriumByTicketID(Integer id);

    List<Auditorium> findAllAuditoriums();

    boolean addAuditorium(HttpServletRequest request);

    boolean updateAuditorium(HttpServletRequest request);

    boolean deleteAuditorium(HttpServletRequest request);
}

