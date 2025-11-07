package com.factor.school.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsDTO{

    private String matricNo;
    @NotEmpty(message = "Tab cannot be empty")
    private String firstname;

    @NotEmpty(message = "Tab cannot be empty")
    private String surname;

@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Email(message = "Input a valid email address")
    private String email;

    @Size(min = 11, max = 11, message = "Invalid phone number")
    private String phoneNumber;

    @Lob
    private byte[] image;

    private String imageType;

}


