package com.factor.security.spring_security.Service;

import com.factor.security.spring_security.Model.Web;
import com.factor.security.spring_security.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service

public class WebService {
    @Autowired
    Repo repo;

    public void addMovies(Web movies, MultipartFile photo) throws IOException {
        movies.setImage(photo.getBytes());
        repo.save(movies);
    }

    public List<Web> getMovies() {
        return repo.findAll();
    }

    public Optional<Web> getByMovieName(String name) {
        return repo.findByMovieNameIgnoreCase(name);
    }

    public Optional<Web> getByreleaseYear(int year) {
        return repo.findByreleaseYear(year);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public boolean deleteByName(String name) {
        if (repo.existsByMovieNameIgnoreCase(name)) {
            repo.deleteByMovieName(name);
            return true;
        }

        return false;
    }
}