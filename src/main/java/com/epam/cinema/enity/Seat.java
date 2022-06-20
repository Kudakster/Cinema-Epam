package com.epam.cinema.enity;

public class Seat extends Entity {
    private Integer seatID;
    private Integer seatRow;
    private Integer seatNumber;
    private Integer auditoriumID;

    public Seat() {}

    public Seat(Integer seatRow, Integer seatNumber, Integer auditoriumID) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.auditoriumID = auditoriumID;
    }

    public Integer getSeatID() {
        return seatID;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public Integer getAuditoriumID() {
        return auditoriumID;
    }

    public void setSeatID(Integer seatID) {
        this.seatID = seatID;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setAuditoriumID(Integer auditoriumID) {
        this.auditoriumID = auditoriumID;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatID=" + seatID +
                ", seatRow=" + seatRow +
                ", seatNumber=" + seatNumber +
                ", auditoriumID=" + auditoriumID +
                '}';
    }
}
