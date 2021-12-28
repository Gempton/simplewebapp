package com.java.mastery.simplewebapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.mastery.simplewebapp.model.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String jobTitle;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Gender gender;
    private Long departmentId;
}
