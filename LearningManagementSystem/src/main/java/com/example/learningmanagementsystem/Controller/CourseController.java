package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.ApiResponse.ApiResponse;
import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseServices;

    @GetMapping("/get")
    public ResponseEntity getCourses() {
        ArrayList<Course> courses = courseServices.getCourses();
        return ResponseEntity.status(200).body(courses);
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        courseServices.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors) {
        boolean isUpdated = courseServices.updateCourse(id, course);
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Course Updated Successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Course Id Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id) {
        boolean isDeleted = courseServices.deleteCourse(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Course Deleted Successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found"));
    }

    @GetMapping("/full/course/{id}")
    public ResponseEntity isCourseFull(@PathVariable String id) {
        boolean isFull = courseServices.isCourseFull(id);
        if (isFull)
            return ResponseEntity.status(200).body(new ApiResponse("Course Is Full"));

        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found"));
    }

    @GetMapping("/same/major/{major}")
    public ResponseEntity getSameMajorCourses(@PathVariable String major) {
        ArrayList<Course> sameMajorCourses = courseServices.getSameMajorCourses(major);

        if (!(major.equalsIgnoreCase("technology") || major.equalsIgnoreCase("science") || major.equalsIgnoreCase("health") || major.equalsIgnoreCase("arts")))
            return ResponseEntity.status(400).body(new ApiResponse("Incorrect Major. Must be One Of These Options: Technology - Science - Health - Arts"));

        return ResponseEntity.status(200).body(sameMajorCourses);
    }

    @GetMapping("/available/{id}")
    public ResponseEntity isAvailableCourse(@PathVariable String id){
        boolean isAvailable = courseServices.isAvailableCourse(id);
        if(isAvailable)
            return ResponseEntity.status(200).body(new ApiResponse("Course Has Available Slots"));

        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found"));
    }

}
