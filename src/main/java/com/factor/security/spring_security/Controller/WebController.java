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
        public void addMovies(@RequestPart("movie") Web movie, @RequestPart("banner") MultipartFile banner) throws IOException {
         webService.addMovies(movie, banner);
}
@GetMapping("/get")
    public List<Web> getMovies(){
    return webService.getMovies();
}

@GetMapping("/get/{movie}")
    public ResponseEntity<Web> getByMovieName(@PathVariable String movie){
    return webService.getMovies().stream()
             .filter(s -> s.getMovieName().equalsIgnoreCase(movie))
             .findFirst()
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());

}

@GetMapping("get/{movie}/banner")
public ResponseEntity<byte []> getMovieBanner(@PathVariable String movie){

    return webService.getByMovieName(movie).stream()
            .filter(s -> s.getMovieName().equalsIgnoreCase(movie))
            .findFirst()
            .map(m -> ResponseEntity.ok()
                    .contentType(MediaType.valueOf(m.getImageType()))
                    .body(m.getImage()))
            .orElse(ResponseEntity.noContent().build());
}


    @DeleteMapping("/delete/{remove}")      //Admin-only
    public ResponseEntity<String> deleteByName(@PathVariable String remove) {
    boolean clear = webService.deleteByName(remove);
    if (clear) {
        return ResponseEntity.ok().body("The item" + remove + "has successfully been deleted");
    }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Item not found");
}
    }