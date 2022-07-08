package com.epam.cinema.enity;

import com.epam.cinema.enity.constraint.TimeIsAfter;
import com.epam.cinema.enity.constraint.TimeInRange;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@TimeIsAfter(fieldName = {"startTime", "endTime"})
public class Screening extends Entity {
    private Integer screeningID;
    @NotNull(message = "error.empty")
    private Integer auditoriumID;
    @NotNull (message = "error.empty")
    private Integer movieID;
    @NotNull (message = "error.empty")
    private Date date;
    @TimeInRange(min = "09:00", max = "22:00")
    @NotNull (message = "error.empty")
    private Time startTime;
    @NotNull (message = "error.empty")
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
    public int hashCode() {
        return Objects.hash(screeningID, auditoriumID, movieID, date, startTime, endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return Objects.equals(screeningID, screening.screeningID) && auditoriumID.equals(screening.auditoriumID) && movieID.equals(screening.movieID) && date.equals(screening.date) && startTime.equals(screening.startTime) && endTime.equals(screening.endTime);
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
