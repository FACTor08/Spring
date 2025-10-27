package com.example.books.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;


@Entity


public class Students {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Override
    public String toString() {
        return "Students{" +
                "matricNo=" + matricNo +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Column(name = "matric_no")
    @Size(min = 9, max = 10, message = "Matric number shouldn't preceed/exceed 10 chars!!!")
    private int matricNo;

    @NotNull(message = "Surname cannot be empty")
    private String surname;

    @NotNull(message = "First_name cannot be empty")
    @Column(name = "first_name")
    private String firstname;

    @Size(min = 1, max = 1)
    private String gender;

    @Temporal(TemporalType.DATE)
    private Date dob;
    @Email(message = "name@example.com")
    private String email;

    @Size(min = 9, max = 11, message = "Phone_number shouldn't preceed/exceed 11 digits!!!")
    @Column(name = "phone_number")
    private int phoneNumber;

    private String imageType;
    @Lob
    private byte[] image;

    public Students(){

    }


    public Students(long id, int matricNo, String surname, String firstname, String gender, Date dob, String email, int phoneNumber, String imageType, byte[] image) {
        this.id = id;

        this.matricNo = matricNo;
        this.surname = surname;
        this.firstname = firstname;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageType = imageType;
        this.image = image;
    }

    public int getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(int matricNo) {
        this.matricNo = matricNo;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
