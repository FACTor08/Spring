package com.example.books.Service;

import com.example.books.Model.Students;
import com.example.books.Repository.StudentsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    @Autowired
    StudentsRepo repo;

    public Students addStudents(Students insert, MultipartFile photo) throws IOException {
        insert.setImage(photo.getBytes());
        return repo.save(insert);
    }

    public List<Students> getStudents() {

        return repo.findAll();

    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }


    public Optional<Students> getByMatricNo(int matricNo) {
        return repo.findByMatricNo(matricNo);
    }

    public Optional<Students> getBySurname(String surname) {
        return repo.getBySurname(surname);
    }
}

