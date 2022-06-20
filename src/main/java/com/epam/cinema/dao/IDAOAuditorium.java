package com.epam.cinema.dao;


import java.util.List;

public interface IDAOAuditorium<Auditorium> {
    Auditorium getAuditoriumById(Integer id);

    Auditorium getAuditoriumByName(String name);

    Auditorium getAuditoriumByTicketID(Integer id);

    List<Auditorium> getAllAuditorium();

    boolean addAuditorium(Auditorium t);

    boolean updateAuditoriumByID(Auditorium t);

    boolean deleteAuditorium(Auditorium t);
}
