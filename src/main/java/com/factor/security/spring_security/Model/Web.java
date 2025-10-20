package com.factor.security.spring_security.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Web {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movie")
    private String movieName;

    @Column(name = "Release_Date")
    @Temporal(TemporalType.DATE)
    private Date releaseYear;
    private String movieCompany;
    @Lob
    private byte[] image;
    private String imageType;

    public Web(){}

    public Web(Long id, String movieName, Date releaseYear, String movieCompany, byte[] image, String imageType){
        this.id = id;
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.movieCompany = movieCompany;
        this.image = image;
        this.imageType = imageType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getMovieCompany() {
        return movieCompany;
    }

    public void setMovieCompany(String movieCompany) {
        this.movieCompany = movieCompany;
    }

    public Date getMovieYear() {
        return releaseYear;
    }

    public void setMovieYear(Date movieYear) {
        this.releaseYear = movieYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
