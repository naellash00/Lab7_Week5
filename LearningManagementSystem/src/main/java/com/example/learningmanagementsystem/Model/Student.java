package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "Name Cannot Be Empty")
    @Size(min = 4, message = "Name Must Be Longer Than 4 Letters")
    private String name;

    @NotEmpty(message = "ID Cannot Be Empty")
    @Size(min = 7, max = 7, message = "ID Must Be 7 digits")
    private String studentID;

    @NotEmpty(message = "Email Cannot Be Empty")
    @Email(message = "Enter A Valid Email")
    private String email;

    @NotNull(message = "GPA Cannot Be Empty")
    @Min(value = 1, message = "GPA Cannot Be Less Than 1")
    @Max(value = 5, message = "GPA Cannot Be More Than 5")
    private double GPA;

    @NotNull(message = "Course Grade Cannot Be Null")
    @Min(value = 10, message = "Course Grade Cannot Be Less Than 10")
    @Max(value = 100, message = "Course Grade Cannot be More Than 100")
    private double courseGrade;

    @AssertFalse
    private boolean enrolled;

    @NotNull(message = "Number Of Absence Cannot Be Empty")
    @Min(0)
    @Max(value = 15, message = "Number Of Absence Cannot Exceed 15")
    private int numberOfAbsence;

}
