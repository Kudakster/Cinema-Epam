package com.epam.cinema.enity;

public class Auditorium extends Entity {
    private Integer auditoriumID;
    private String auditoriumName;

    public Auditorium() {}

    public Auditorium(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public Integer getAuditoriumID() {
        return auditoriumID;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumID(Integer auditoriumID) {
        this.auditoriumID = auditoriumID;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }
}
