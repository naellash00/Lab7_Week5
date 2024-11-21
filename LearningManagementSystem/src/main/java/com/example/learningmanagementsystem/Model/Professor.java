package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Professor {

    @NotEmpty(message = "Name Cannot Be Empty")
    @Size(min = 4, message = "Name Must Be Longer Than 4 Letters")
    private String name;

    @NotEmpty(message = "ID Cannot Be Empty")
    @Size(min = 7, max = 7, message = "ID Must Be 7 digits")
    private String id;

    @NotEmpty(message = "Department Cannot Be Empty")
    @Pattern(regexp = "^(Math|Science|Engineering|Arts)$", message = "Department Must Be one of these: Math - science - engineering - arts")
    private String department;

    @NotNull(message = "years cannot be empty")
    @Min(value = 1 , message = "experinece cannot be less than 1 year")
    private int yearsOfExperience;
}
