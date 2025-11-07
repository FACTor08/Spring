package com.factor.school.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student_details {

    @Id
    private String matricNo;

    private String firstname;
    private String surname;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Email(message = "Input a valid email address")
    private String email;

    @Size(min = 11, max = 11, message = "Invalid phone number")
    @Column(name = "Phone")
    private String phoneNumber;

    @Lob
    private byte[] image;

    private String imageType;

}
