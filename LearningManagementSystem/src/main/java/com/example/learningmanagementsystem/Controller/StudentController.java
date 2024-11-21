package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.ApiResponse.ApiResponse;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentServices;

    @GetMapping("/get")
    public ResponseEntity getStudents() {
        ArrayList<Student> students = studentServices.getStudents();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        studentServices.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        studentServices.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        boolean isDeleted = studentServices.deleteStudent(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Student Deleted Successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found"));
    }

    @PutMapping("/enroll/{id}")
    public ResponseEntity enrollStudentInCourse(@PathVariable String id) {
        boolean isEnrolled = studentServices.enrollStudentInCourse(id);
        if (isEnrolled)
            return ResponseEntity.status(200).body(new ApiResponse("Student Enrolled Successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found"));
    }

    @PutMapping("/add/absence/{id}")
    public ResponseEntity addAbsence(@PathVariable String id) {
        boolean isAbsent = studentServices.addAbsence(id);
        if (isAbsent)
            return ResponseEntity.status(200).body(new ApiResponse("Student Absence Entered Successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Student ID Not Found"));
    }

    @GetMapping("/pass/course/{id}")
    public ResponseEntity passedCourse(@PathVariable String id){
        String passOrNot = studentServices.passedCourse(id);
        if(passOrNot.equalsIgnoreCase("passed"))
            return ResponseEntity.status(200).body(new ApiResponse("Student Passed The Course"));

        if(passOrNot.equalsIgnoreCase("failed"))
            return ResponseEntity.status(200).body(new ApiResponse("Student Did Not Pass The Course"));

        return ResponseEntity.status(400).body(new ApiResponse("Student ID Not Found"));
    }


}
