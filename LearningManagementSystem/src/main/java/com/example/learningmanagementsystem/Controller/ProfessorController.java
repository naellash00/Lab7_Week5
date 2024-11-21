package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.ApiResponse.ApiResponse;
import com.example.learningmanagementsystem.Model.Professor;
import com.example.learningmanagementsystem.Service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/professor")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService professorServices;
    private final ProfessorService professorService;

    @GetMapping("/get")
    public ResponseEntity getProfessors() {
        ArrayList<Professor> professors = professorServices.getProfessors();
        return ResponseEntity.status(200).body(professors);
    }

    @PostMapping("/add")
    public ResponseEntity addProfessor(@RequestBody @Valid Professor prof, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        professorService.addProfessor(prof);
        return ResponseEntity.status(200).body(new ApiResponse("professor Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProf(@PathVariable String id, @RequestBody @Valid Professor prof, Errors errors) {
        boolean isUpdated = professorServices.updateProfessor(id, prof);
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("professor update Successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProfessor(@PathVariable String id) {
        boolean isDeleted = professorServices.deleteProfessor(id);

        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @PutMapping("/change/dep/{id}/{dep}")
    public ResponseEntity assignToDepartment(@PathVariable String id, @PathVariable String dep) {
        if (!(dep.equalsIgnoreCase("math") || dep.equalsIgnoreCase("science") || dep.equalsIgnoreCase("engineering") || dep.equalsIgnoreCase("arts"))) {
            return ResponseEntity.status(400).body(new ApiResponse("incorrect department"));
        }
        professorService.assignToDepartment(id, dep);
        return ResponseEntity.status(200).body(new ApiResponse("department changed successfully"));
    }

    @GetMapping("/same/years/{years}")
    public ResponseEntity getSameYears(@PathVariable int years) {
        ArrayList<Professor> sameyears = professorServices.getSameYears(years);
        return ResponseEntity.status(200).body(sameyears);
    }

    @GetMapping("/same/dep/{dep}")
    public ResponseEntity getSameDep(@PathVariable String dep) {
        ArrayList<Professor> samedep = professorServices.getSameDep(dep);
        return ResponseEntity.status(200).body(samedep);
    }

}
