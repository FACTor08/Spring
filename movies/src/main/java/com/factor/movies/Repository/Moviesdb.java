package com.factor.movies.Repository;

import com.factor.movies.Model.Movies;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.objenesis.instantiator.basic.DelegatingToExoticInstantiator;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Moviesdb extends JpaRepository<Movies, Long> {

    Optional<Movies> findByTitle(String title);
    Optional<Movies> deleteByTitleIgnoreCase(String title);
    Boolean existsByTitle(String title);

}
