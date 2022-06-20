package com.epam.cinema.enity;

import java.sql.Date;
import java.sql.Time;

public class Screening extends Entity {
    private Integer screeningID;
    private Integer auditoriumID;
    private Integer movieID;
    private Date date;
    private Time startTime;
    private Time endTime;

    public Integer getScreeningID() {
        return screeningID;
    }

    public Integer getAuditoriumID() {
        return auditoriumID;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setScreeningID(Integer screeningID) {
        this.screeningID = screeningID;
    }

    public void setAuditoriumID(Integer auditoriumID) {
        this.auditoriumID = auditoriumID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "screeningID=" + screeningID +
                ", auditoriumID=" + auditoriumID +
                ", movieID=" + movieID +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
