package com.factor.school.Helper;

import com.factor.school.Model.DetailsDTO;
import com.factor.school.Model.Student_details;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

@Component
public class DataTransfer {

    private static final Random random = new Random();

    public String matricGenerator(){
        return "SCH" + LocalDate.now().getYear() + (1000 + random.nextInt(9000));
    }

    public Student_details convert(DetailsDTO detailsDTO, byte[] photo, String imageType)throws IOException {
        Student_details student = new Student_details();

        student.setMatricNo(matricGenerator());
        student.setFirstname(detailsDTO.getFirstname());
        student.setSurname(detailsDTO.getSurname());
        student.setDob(detailsDTO.getDob());
        student.setEmail(detailsDTO.getEmail());
        student.setPhoneNumber(detailsDTO.getPhoneNumber());
        student.setImage(photo);
        student.setImageType(imageType);
      return student;
    }

}
