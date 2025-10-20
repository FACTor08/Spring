package com.factor.security.spring_security.Repository;

import com.factor.security.spring_security.Model.Web;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repo extends JpaRepository<Web, Long> {

    void deleteByMovieName(String movieName);

  boolean existsByMovieNameIgnoreCase(String movieName);

    Optional<Web> findByMovieName(String movieName);

    Optional<Web> findByreleaseYear(int releaseYear);
}
