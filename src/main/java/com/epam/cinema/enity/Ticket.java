package com.epam.cinema.enity;

public class Ticket extends Entity {
    private Integer ticketID;
    private Integer userID;
    private Integer seatReservedID;

    public Ticket() {}

    public Ticket(Integer userID, Integer seatReservedID) {
        this.userID = userID;
        this.seatReservedID = seatReservedID;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public Integer getUserID() {
        return userID;
    }

    public Integer getSeatReservedID() {
        return seatReservedID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setSearReservedID(Integer seatReservedID) {
        this.seatReservedID = seatReservedID;
    }
}
