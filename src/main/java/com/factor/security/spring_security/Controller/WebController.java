package com.factor.security.spring_security.Controller;

import com.factor.security.spring_security.Model.Web;
import com.factor.security.spring_security.Service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movieweb")
public class WebController {
    @Autowired
    WebService webService;
@PostMapping(value = "/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)  //Admin-only
        public ResponseEntity<String> addMovies(@RequestPart("movie") Web movie, @RequestPart("banner") MultipartFile banner) throws IOException {
         webService.addMovies(movie, banner);
         return ResponseEntity.ok("movie uploaded Successfully");     //Uploads movies to the DB
}
@GetMapping("/get")
    public List<Web> getMovies(){
    return webService.getMovies();  //Returns all movies
}

@GetMapping("/get/{movie}")
    public ResponseEntity<Web> getByMovieName(@PathVariable String movie){
            return webService.getByMovieName(movie).stream()
                    .findFirst()
                    .map( ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
}

@GetMapping("get/{movie}/banner")
public ResponseEntity<byte []> getMovieBanner(@PathVariable String movie){          //Obtain the movie banner
    return webService.getByMovieName(movie).stream()
            .findFirst()
            .map(mapper -> ResponseEntity.ok()
                    .contentType(MediaType.valueOf(mapper.getImageType()))
                    .body(mapper.getImage()))
            .orElse(ResponseEntity.noContent().build());
}


    @DeleteMapping("/delete/{remove}")      //Admin-only
    public ResponseEntity<String> deleteByName(@PathVariable String remove) {
    boolean clear = webService.deleteByName(remove);
    if (clear) {
        return ResponseEntity.ok().body("The item" + remove + "has successfully been deleted");
    }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Movie not found");
}
    }