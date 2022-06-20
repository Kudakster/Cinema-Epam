package com.epam.cinema.service;

import com.epam.cinema.service.implementation.*;

public class ServiceFactory {
    public static UserServiceImpl getUserService() {
        return UserServiceImpl.getInstance();
    }

    public static MovieServiceImpl getMovieService() {
        return MovieServiceImpl.getInstance();
    }

    public static AuditoriumServiceImpl getAuditoriumService() {
        return AuditoriumServiceImpl.getInstance();
    }

    public static SeatServiceImpl getSeatService() {
        return SeatServiceImpl.getInstance();
    }

    public static TicketServiceImpl getTicketService() {
        return TicketServiceImpl.getInstance();
    }

    public static SeatReservedServiceImpl getSeatReservedService() {
        return SeatReservedServiceImpl.getInstance();
    }

    public static ScreeningServiceImpl getScreeningService() {
        return ScreeningServiceImpl.getInstance();
    }
}
