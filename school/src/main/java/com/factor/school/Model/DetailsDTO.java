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
    private String firstname;
    private String surname;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dob;

    private String email;
    private String phone;
    private String imageType;

}


