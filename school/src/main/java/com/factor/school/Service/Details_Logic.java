package com.factor.school.Service;

import com.factor.school.Configuration.DataTransfer;
import com.factor.school.Model.DetailsDTO;
import com.factor.school.Model.Student_details;
import com.factor.school.Repository.MainDB;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Details_Logic {
    @Autowired
    MainDB repo;

    @Autowired
    private DataTransfer dt;

    public String enrollStudents(DetailsDTO details, MultipartFile photo) throws IOException {
        byte[] image = photo.getBytes();
        String imageType = photo.getContentType();
        Student_details student = dt.transfer(details, image, imageType);
        repo.save(student);
    return "Student has successfully been enrolled with the Matric number " + details.getMatricNo();
    }


    public List<Student_details> getAllStudents(){
      return repo.findAll();
    }


    public Optional<Student_details> findBySurname(String surname){
      return repo.findBySurnameIgnoreCase(surname);
    }


    public Optional<Student_details> findByMatricNo(String matric){
      return repo.findByMatricNoIgnoreCase(matric);
    }


    public boolean eraseStudentData(String matric){
        if(repo.existsByMatricNoIgnoreCase(matric)){
            repo.deleteByMatricNoIgnoreCase(matric);
            return  true;
        }
        else{
            return false;
        }
    }
}
