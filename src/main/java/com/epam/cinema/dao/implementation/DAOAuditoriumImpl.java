package com.epam.cinema.dao.implementation;

import com.epam.cinema.dao.DAOGeneral;
import com.epam.cinema.dao.IDAOAuditorium;
import com.epam.cinema.dao.mysql.MySQLConstants;
import com.epam.cinema.dao.utils.Mapper;
import com.epam.cinema.enity.Auditorium;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOAuditoriumImpl extends DAOGeneral<Auditorium> implements IDAOAuditorium<Auditorium> {

    public DAOAuditoriumImpl() {
        super(Auditorium::new);
        Mapper<ResultSet, Auditorium> auditoriumMapper = (ResultSet resultSet, Auditorium auditorium) -> {
            auditorium.setAuditoriumID(resultSet.getInt(1));
            auditorium.setAuditoriumName(resultSet.getString(2));
        };
        super.setObjectMapper(auditoriumMapper);

        Mapper<Auditorium, PreparedStatement> statementMapper = (Auditorium auditorium, PreparedStatement preparedStatement) -> {
            preparedStatement.setString(1, auditorium.getAuditoriumName());
        };
        super.setStatementMapper(statementMapper);
    }

    @Override
    public Auditorium getAuditoriumById(Integer id) {
        return get(MySQLConstants.Auditorium.SQL_GET_AUDITORIUM_BY_ID, id);
    }

    @Override
    public Auditorium getAuditoriumByName(String name) {
        return get(MySQLConstants.Auditorium.SQL_GET_AUDITORIUM_BY_NAME, name);
    }

    @Override
    public Auditorium getAuditoriumByTicketID(Integer id) {
        return get(MySQLConstants.Auditorium.SQL_GET_AUDITORIUM_BY_TICKET_ID, id);
    }

    @Override
    public List<Auditorium> getAllAuditorium() {
        return getAll(MySQLConstants.Auditorium.SQL_GET_ALL_AUDITORIUMS);
    }

    @Override
    public boolean addAuditorium(Auditorium auditorium) {
        return add(auditorium, MySQLConstants.Auditorium.SQL_ADD_AUDITORIUM);
    }

    @Override
    public boolean updateAuditoriumByID(Auditorium auditorium) {
        return update(auditorium, MySQLConstants.Auditorium.SQL_UPDATE_AUDITORIUM_BY_ID, 2, auditorium.getAuditoriumID());
    }

    @Override
    public boolean deleteAuditorium(Auditorium auditorium) {
        return delete(auditorium.getAuditoriumID(), MySQLConstants.Auditorium.SQL_DELETE_AUDITORIUM_BY_ID);
    }
}
