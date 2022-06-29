package com.epam.cinema.dao.mysql;

public class MySQLConstants {

    public static final class User {
        public static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE user_id=?;";
        public static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE user_login=?;";
        public static final String SQL_GET_USER_BY_ROLE = "SELECT * FROM users WHERE user_role=?;";
        public static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
        public static final String SQL_DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE user_login=?;";
        public static final String SQL_ADD_USER = "INSERT INTO users (user_login, user_password, user_firstname, user_surname, user_email, user_phoneNumber, user_role) \n" +
                "values(?, ?, ?, ?, ?, ?, ?)";
        public static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET " +
                "user_login=?, user_password=?, user_firstname=?, user_surname=?, user_email=?, user_phoneNumber=?, user_role=? " +
                "WHERE user_id=?;";
    }

    public static final class Movie {
        public static final String SQL_GET_MOVIE_BY_ID = "SELECT * FROM movies WHERE movie_id=?;";
        public static final String SQL_GET_MOVIE_BY_NAME = "SELECT * FROM movies WHERE movie_name=?;";
        public static final String SQL_GET_MOVIE_BY_TICKET_ID = "SELECT * FROM cinema.movies WHERE movie_id IN\n" +
                "(SELECT movie_id FROM cinema.screenings WHERE screening_id IN\n" +
                "(SELECT screening_id FROM cinema.seat_reserved WHERE seat_reserved_id IN\n" +
                "(SELECT seat_reserved_id FROM cinema.tickets WHERE ticket_id=?)));";
        public static final String SQL_GET_MOVIE_BY_NAME_PATTERN = "SELECT * FROM movies WHERE movie_name LIKE ?;";
        public static final String SQL_GET_ALL_MOVIES = "SELECT * FROM movies";
        public static final String SQL_GET_MOVIES_BY_PAGINATION = "SELECT * FROM movies LIMIT ?, ?";
        public static final String SQL_DELETE_MOVIE_BY_ID = "DELETE FROM movies WHERE movie_id=?;";
        public static final String SQL_COUNT_ALL_MOVIES = "SELECT COUNT(*) FROM movies;";
        public static final String SQL_ADD_MOVIE = "INSERT INTO movies (movie_name, movie_actors, movie_direction, movie_genre, movie_country, movie_trailer_url, movie_img_url, movie_release_date, movie_duration_min, movie_description) \n" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String SQL_UPDATE_MOVIE_BY_ID = "UPDATE movies SET " +
                "movie_name=?, movie_actors=?, movie_direction=?, movie_genre=?, movie_country=?, movie_trailer_url=?, movie_img_url=?, movie_release_date=?, movie_duration_min=?, movie_description=? " +
                "WHERE movie_id=?;";
    }

    public static final class Auditorium {
        public static final String SQL_GET_AUDITORIUM_BY_ID = "SELECT * FROM auditorium WHERE auditorium_id=?;";
        public static final String SQL_GET_AUDITORIUM_BY_NAME = "SELECT * FROM auditorium WHERE auditorium_name=?;";
        public static final String SQL_GET_AUDITORIUM_BY_TICKET_ID = "SELECT * FROM cinema.auditorium WHERE auditorium_id IN\n" +
                "(SELECT auditorium_id FROM cinema.seats WHERE seat_id IN\n" +
                "(SELECT seat_id FROM cinema.seat_reserved WHERE seat_reserved_id IN\n" +
                "(SELECT seat_reserved_id FROM cinema.tickets WHERE ticket_id=?)));";
        public static final String SQL_GET_ALL_AUDITORIUMS = "SELECT * FROM auditorium";
        public static final String SQL_DELETE_AUDITORIUM_BY_ID = "DELETE FROM auditorium WHERE auditorium_id=?;";
        public static final String SQL_ADD_AUDITORIUM = "INSERT INTO auditorium (auditorium_name) " +
                "values(?)";
        public static final String SQL_UPDATE_AUDITORIUM_BY_ID = "UPDATE auditorium SET " +
                "auditorium_name=? WHERE auditorium_id=?;";
    }

    public static final class Screening {
        public static final String SQL_GET_SCREENING_BY_ID = "SELECT * FROM screenings WHERE screening_id=?;";
        public static final String SQL_GET_SCREENING_BY_TICKET_ID = "SELECT * FROM cinema.screenings WHERE screening_id IN\n" +
                "(SELECT screening_id FROM cinema.seat_reserved WHERE seat_reserved_id IN\n" +
                "(SELECT seat_reserved_id FROM cinema.tickets WHERE ticket_id=?));";
        public static final String SQL_GET_ALL_SCREENINGS = "SELECT * FROM screenings";
        public static final String SQL_GET_SCREENINGS_BY_DATE = "SELECT * FROM screenings WHERE screening_date=? AND screening_start_time>=? ORDER BY screening_start_time;";
        public static final String SQL_GET_SCREENING_BY_DATE_AND_AUDITORIUM_ID = "SELECT * FROM screenings WHERE screening_date>=? AND auditorium_id=?;";
        public static final String SQL_GET_SCREENINGS_BY_PAGINATION = "SELECT * FROM screenings LIMIT ?, ?";
        public static final String SQL_GET_SCREENINGS_BY_DATE_WITH_PAGINATION = "SELECT * FROM cinema.screenings WHERE screening_date>=?  \n" +
                "ORDER BY screening_date LIMIT ?, ?;";
        public static final String SQL_DELETE_SCREENING_BY_ID = "DELETE FROM screenings WHERE screening_id=?;";
        public static final String SQL_DELETE_SCREENINGS_BY_DATE = "DELETE FROM screenings WHERE screening_date= IN";
        public static final String SQL_ADD_SCREENING = "INSERT INTO screenings (auditorium_id, movie_id, screening_date, screening_start_time, screening_end_time ) " +
                "values(?, ?, ?, ?, ?)";
        public static final String SQL_UPDATE_SCREENING_BY_ID = "UPDATE screenings SET " +
                "auditorium_id=?, movie_id=?, screening_date=?, screening_start_time=?, screening_end_time=? WHERE screening_id=?;";
    }

    public static final class Seat {
        public static final String SQL_GET_SEAT_BY_ID = "SELECT * FROM seats WHERE seat_id=?;";
        public static final String SQL_GET_SEAT_BY_ROW_AND_NUMBER = "SELECT * FROM seats WHERE seat_row=? AND seat_number=?;";
        public static final String SQL_GET_SEAT_BY_TICKET_ID = "SELECT * FROM cinema.seats WHERE seat_id IN\n" +
                "(SELECT seat_id FROM cinema.seat_reserved WHERE seat_reserved_id IN\n" +
                "(SELECT seat_reserved_id FROM cinema.tickets WHERE ticket_id=?));";
        public static final String SQL_GET_ALL_SEATS = "SELECT * FROM seats";
        public static final String SQL_GET_ALL_SEATS_BY_AUDITORIUM_ID = "SELECT * FROM seats WHERE auditorium_id=?";
        public static final String SQL_DELETE_SEAT_BY_ID = "DELETE FROM seats WHERE seat_id=?;";
        public static final String SQL_DELETE_SEATS_BY_ID = "DELETE FROM seats WHERE seat_id IN";
        public static final String SQL_ADD_SEAT = "INSERT INTO seats (auditorium_id, seat_row, seat_number) " +
                "values(?, ?, ?)";
    }

    public static final class SeatReserved {
        public static final String SQL_GET_SEAT_RESERVED_BY_ID = "SELECT * FROM seat_reserved WHERE seat_reserved_id=?;";
        public static final String SQL_GET_SEAT_RESERVED_BY_SEAT_ID_AND_SCREENING_ID = "SELECT * FROM seat_reserved WHERE seat_id=? AND screening_id=?;";
        public static final String SQL_GET_ALL_SEAT_RESERVED = "SELECT * FROM seat_reserved";
        public static final String SQL_GET_ALL_SEAT_RESERVED_BY_SCREENING_ID = "SELECT * FROM seat_reserved WHERE screening_id=?";
        public static final String SQL_DELETE_SEAT_RESERVED_BY_ID = "DELETE FROM seat_reserved WHERE seat_reserved_id=?;";
        public static final String SQL_ADD_SEAT_RESERVED = "INSERT INTO seat_reserved (seat_id, screening_id) " +
                "values(?, ?)";
        public static final String SQL_UPDATE_SEAT_RESERVED_BY_ID = "UPDATE seat_reserved SET " +
                "seat_id=?, screening_id=?;";
    }

    public static final class Ticket {
        public static final String SQL_GET_TICKET_BY_ID = "SELECT * FROM tickets WHERE ticket_id=?;";
        public static final String SQL_GET_ALL_TICKETS_BY_USER_ID = "SELECT * FROM tickets WHERE user_id=?;";
        public static final String SQL_GET_ALL_TICKETS_BY_USER_ID_AND_CURRENT_DATE = "SELECT * FROM cinema.tickets WHERE user_id=? AND seat_reserved_id IN\n" +
                "(SELECT seat_reserved_id FROM cinema.seat_reserved WHERE screening_id IN\n" +
                "(SELECT screening_id FROM cinema.screenings WHERE screening_date>=?)) ";
        public static final String SQL_GET_ALL_TICKETS = "SELECT * FROM tickets";
        public static final String SQL_DELETE_TICKET_BY_ID = "DELETE FROM tickets WHERE ticket_id=?;";
        public static final String SQL_ADD_TICKET = "INSERT INTO tickets (user_id, seat_reserved_id) " +
                "values(?, ?)";
        public static final String SQL_UPDATE_TICKET_BY_ID = "UPDATE tickets SET " +
                "auditorium_id=?, seat_row=?, seat_number=? WHERE ticket_id=?;";
    }
}
