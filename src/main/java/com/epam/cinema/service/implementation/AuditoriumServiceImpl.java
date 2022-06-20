package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOAuditoriumImpl;
import com.epam.cinema.enity.Auditorium;
import com.epam.cinema.service.IAuditorium;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AuditoriumServiceImpl implements IAuditorium {
    private final DAOAuditoriumImpl daoAuditorium;
    private static AuditoriumServiceImpl instance = null;

    private static final String AUDITORIUM = "auditorium";
    private static final String AUDITORIUM_ID = "auditorium-id";
    private static final String AUDITORIUM_NAME = "name";

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
    public boolean addAuditorium(HttpServletRequest request) {
        String auditoriumName = request.getParameter(AUDITORIUM_NAME);

        Auditorium auditorium = new Auditorium(auditoriumName);

        return daoAuditorium.addAuditorium(auditorium);
    }

    @Override
    public boolean updateAuditorium(HttpServletRequest request) {
        Auditorium auditorium = (Auditorium) request.getSession().getAttribute("auditorium");
        String auditoriumName = request.getParameter(AUDITORIUM_NAME);

        auditorium.setAuditoriumName(auditoriumName);

        return daoAuditorium.updateAuditoriumByID(auditorium);
    }

    @Override
    public boolean deleteAuditorium(HttpServletRequest request) {
        Auditorium auditorium = (Auditorium) request.getAttribute(AUDITORIUM);
        return daoAuditorium.deleteAuditorium(auditorium);
    }

    public static AuditoriumServiceImpl getInstance() {
        if (instance == null)
            instance = new AuditoriumServiceImpl();
        return instance;
    }
}
