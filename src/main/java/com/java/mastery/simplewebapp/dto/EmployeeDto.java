package com.java.mastery.simplewebapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.mastery.simplewebapp.model.enums.Gender;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class EmployeeDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String jobTitle;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

    @NotNull
    private Long departmentId;
}
