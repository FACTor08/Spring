package com.example.books.Controller;
import com.example.books.Model.Students;
import com.example.books.Service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/school")


public class StudentsController {

@Autowired
    StudentsService studentsService;
    Students store;

    @PostMapping(value = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)       //upload of student data including Student photo id
    public ResponseEntity<?> addStudent(@RequestPart("insert") Students insert, @RequestPart("photos") MultipartFile photos)throws IOException {
        return ResponseEntity.ok(studentsService.addStudents(insert, photos));
    }
@GetMapping("/get")
    public List<Students> getDetails(){
//get list of registered students
     return studentsService.getStudents();
    }

@GetMapping("/get/{matric_no}/picture")   //get Student photoId by calling matric number
public ResponseEntity<byte []> getPhoto(@PathVariable int matric_no){

    return studentsService.getByMatricNo(matric_no)
            .map(students -> ResponseEntity.ok()
                    .contentType(MediaType.valueOf(students.getImageType()))
                    .body(students.getImage()))
            .orElse(ResponseEntity.noContent().build());
}


    @GetMapping("/get/surname/{surname}")  //find student by Surname
public ResponseEntity<Students> getBySurname(@PathVariable String surname){
    return studentsService.getBySurname(surname).stream()
            .filter(students -> students.getSurname().equalsIgnoreCase(surname))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


    @GetMapping("/get/matric_no/{matric_no}")
public ResponseEntity<Students> getByMatricNo(@PathVariable int matric_no){
    return studentsService.getByMatricNo(matric_no)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


@DeleteMapping("/delete/{delete}")
    public ResponseEntity<String> erase(@PathVariable Long delete){
         studentsService.deleteById(delete);

         return ResponseEntity.ok().body("Successfully deleted details of student: " + delete);

    }

}