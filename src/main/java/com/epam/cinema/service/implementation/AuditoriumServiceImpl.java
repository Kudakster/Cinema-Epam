package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOAuditoriumImpl;
import com.epam.cinema.enity.Auditorium;
import com.epam.cinema.service.IAuditorium;

import java.util.List;

public class AuditoriumServiceImpl implements IAuditorium {
    private DAOAuditoriumImpl daoAuditorium;
    private static AuditoriumServiceImpl instance = null;

    public AuditoriumServiceImpl() {
        daoAuditorium = new DAOAuditoriumImpl();
    }

    @Override
    public Auditorium findAuditoriumById(Integer id) {
        return daoAuditorium.getAuditoriumById(id);
    }

    @Override
    public Auditorium findAuditoriumByName(String name) {
        return daoAuditorium.getAuditoriumByName(name);
    }

    @Override
    public Auditorium findAuditoriumByTicketID(Integer id) {
        return daoAuditorium.getAuditoriumByTicketID(id);
    }

    @Override
    public List<Auditorium> findAllAuditoriums() {
        return daoAuditorium.getAllAuditorium();
    }

    @Override
    public boolean addAuditorium(Auditorium auditorium) {
        return daoAuditorium.addAuditorium(auditorium);
    }

    @Override
    public boolean updateAuditorium(Auditorium auditorium) {
        return daoAuditorium.updateAuditoriumByID(auditorium);
    }

    @Override
    public boolean deleteAuditorium(Auditorium auditorium) {
        return daoAuditorium.deleteAuditorium(auditorium);
    }

    public static AuditoriumServiceImpl getInstance() {
        if (instance == null)
            instance = new AuditoriumServiceImpl();
        return instance;
    }

    public static void setInstance(AuditoriumServiceImpl instance) {
        AuditoriumServiceImpl.instance = instance;
    }
}
