package com.epam.cinema.enity;

public class SeatReserved extends Entity {
    private Integer seatReservedID;
    private Integer seatID;
    private Integer screeningID;

    public SeatReserved() {}

    public SeatReserved(Integer seatID, Integer screeningID) {
        this.seatID = seatID;
        this.screeningID = screeningID;
    }

    public Integer getSeatReservedID() {
        return seatReservedID;
    }

    public Integer getSeatID() {
        return seatID;
    }

    public Integer getScreeningID() {
        return screeningID;
    }

    public void setSeatReservedID(Integer seatReservedID) {
        this.seatReservedID = seatReservedID;
    }

    public void setSeatID(Integer seatID) {
        this.seatID = seatID;
    }

    public void setScreeningID(Integer screeningID) {
        this.screeningID = screeningID;
    }
}
