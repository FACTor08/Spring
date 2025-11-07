package com.factor.school.Controller;

import com.factor.school.Model.DetailsDTO;
import com.factor.school.Model.Student_details;
import com.factor.school.Service.Details_Logic;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/school")

public class Main {
    @Autowired
    private Details_Logic logic;

    @PostMapping(value = "/enroll", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<String> enrollStudent(@ModelAttribute DetailsDTO detail,
                                                  @RequestPart("photo") MultipartFile photo) throws IOException {
       Student_details data = logic.enrollStudents(detail, photo);

      return ResponseEntity.ok()
               .body(detail.getFirstname() + " " + detail.getSurname() + "  has been Successfully " +
                       "enrolled with MatricNo: " + data.getMatricNo());

    }

   @GetMapping("/registered")

   public ResponseEntity<List<Student_details>> allEnrolledStudents(){
       List<Student_details> students = logic.getAllStudents();
       return ResponseEntity.ok(students);
   }

   @GetMapping("/search/{surname}")

   public ResponseEntity<Student_details> findBySurname(@PathVariable String surname){
       return logic.findBySurname(surname).stream()
              .findFirst()
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.status(404).build());

   }


    @GetMapping("/search/{matric_no}/students")
    public ResponseEntity<Student_details> findByMatricNo(@PathVariable String matric_no){
        return logic.findByMatricNo(matric_no).stream()
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());

    }

   @DeleteMapping("/erase/{idCode}")
   public ResponseEntity<String> deleteStudentData(@PathVariable String idCode){
        boolean erased = logic.eraseStudentData(idCode);
        if (erased){
            return ResponseEntity.ok("Data of Student registered with id-code " + idCode + " Successfully deleted");
   }
        else {
            return ResponseEntity.status(404).body("no Registered Student with id-code " + idCode + " found");
        }
   }
}
