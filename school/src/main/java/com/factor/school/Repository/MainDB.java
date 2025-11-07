package com.factor.school.Repository;

import com.factor.school.Model.Student_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainDB extends JpaRepository<Student_details, String> {

    Optional<Student_details> findByMatricNoIgnoreCase(String matricNo);

    Optional<Student_details> findBySurnameIgnoreCase(String surname);

    Boolean existsByMatricNoIgnoreCase(String matricNo);

    void deleteByMatricNoIgnoreCase(String matricNo);

}