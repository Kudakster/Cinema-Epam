package com.epam.cinema.enity;

import javax.validation.constraints.Size;
import java.sql.Date;

public class Movie extends Entity {
    private Integer id;

    @Size(min = 2, max = 30,
    message = "Error")
    private String name;
    private String actors;
    private String direction;
    private String genre;
    private String country;
    private String trailerURL;
    private String imgURL;
    private Date releaseDate;
    private String durationMin;
    private String description;

    public Movie() {}

    public Movie(String name, Date releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getActors() {
        return actors;
    }

    public String getDirection() {
        return direction;
    }

    public String getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getDurationMin() {
        return durationMin;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDurationMin(String durationMin) {
        this.durationMin = durationMin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", actors='" + actors + '\'' +
                ", direction='" + direction + '\'' +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                ", trailerURL='" + trailerURL + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", durationMin='" + durationMin + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
