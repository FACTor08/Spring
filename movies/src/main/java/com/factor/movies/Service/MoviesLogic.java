package com.factor.movies.Service;

import com.factor.movies.Configuration.MovieDTransfer;
import com.factor.movies.Model.Movies;
import com.factor.movies.Model.MoviesDTO;
import com.factor.movies.Repository.Moviesdb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MoviesLogic {

    @Autowired
    private Moviesdb repo;
    @Autowired
    private  MovieDTransfer trnsf;

    public Movies uploadMovies(MoviesDTO data, MultipartFile movie, MultipartFile banner, MultipartFile subtitle) throws IOException {

        byte[] image = banner.getBytes();
        byte[] sub = subtitle.getBytes();
        byte[] visuals = movie.getBytes();
        String imageType = banner.getContentType();

        Movies store = trnsf.transferObj(image,visuals,sub,data,imageType);
        return repo.save(store);
    }

    public List<Movies> getAllMovies() {
        return repo.findAll();
    }

    public Optional<Movies> getByTitle(String title) {
        return repo.findByTitle(title);
    }

    public Boolean patchData(String title){
            Optional<Movies> optionalData = repo.findByTitle(title);
            if(optionalData.isPresent()){
        Movies data = optionalData.get();
                data.setTitle(title);
                repo.save(data);
                return true;
            }
            else {
                return false;
            }
    }
}