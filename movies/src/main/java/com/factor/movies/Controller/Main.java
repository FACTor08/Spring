package com.factor.movies.Controller;

import com.factor.movies.Model.Movies;
import com.factor.movies.Model.MoviesDTO;
import com.factor.movies.Service.MoviesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/factormovies")
public class Main {

    @Autowired
    private MoviesLogic logic;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadMovies(@ModelAttribute MoviesDTO data, @RequestPart("movie") MultipartFile movie,
                                               @RequestPart("subtitle") MultipartFile subtitle,
                                                  @RequestPart("banner") MultipartFile banner) throws IOException {
        System.out.println(
                SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        );
        logic.uploadMovies(data, movie, subtitle, banner);
        return ResponseEntity.ok("Movie: " + data.getTitle() + " has successfully been uploaded");

    }

    @GetMapping("/movies")
    public List<Movies> getAllMovies() {
        return logic.getAllMovies();
    }

    @GetMapping("/movies/search/{title}")
    public ResponseEntity<Movies> getByTitle(@PathVariable String title) {
        return logic.getByTitle(title).stream()
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}