package com.example.books.Repository;

import com.example.books.Model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface StudentsRepo extends JpaRepository<Students, Long>{
        Optional<Students> getBySurname(String surname);
        Optional<Students> findByMatricNo(int matricNo);
}
