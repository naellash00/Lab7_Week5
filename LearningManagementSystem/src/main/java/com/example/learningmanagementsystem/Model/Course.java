package com.example.learningmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "Course ID Cannot Be Empty")
    @Size(min = 5, max = 5, message = "Course ID Must Be 5")
    @Pattern(regexp = "^[a-zA-Z]{2}\\d{3}$", message = "Course ID Must Start With 2 Letters Followed By 3 Numbers. (ex. cs102)")
    private String ID;

    @NotEmpty(message = "Course ID Cannot Be Empty")
    @Size(min = 4, max = 25, message = "Course Name Must Be Between 4-25 Letters")
    private String name;

    @NotNull(message = "Number Of Students Cannot Be Empty")
    @Max(value = 40, message = "Course Can Have More Than 40 Students")
    @Min(value = 5, message = "Course Can Have Less Than 5 Students")
    private int numberOfStudents;

    @NotEmpty(message = "Schedule Cannot Be Empty")
    @Pattern(regexp = "^(Monday|Tuesday|Wednesday|Thursday|Sunday)-\\d{1,2}-\\d{2}$", message = "Enter Valid Schedule Format")
    private String lectureTime;

    @NotNull(message = "Start Date Cannot Be Empty")
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End Date Cannot Be Empty")
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "Course Related Major Cannot Be Empty")
    @Pattern(regexp = "^(technology|science|health|arts)$", message = "Course Related Major Must be One Of These Options: Technology - Science - Health - Arts")
    private String courseRelatedMajor;
}
