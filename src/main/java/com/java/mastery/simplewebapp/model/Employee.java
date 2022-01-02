package com.java.mastery.simplewebapp.model;

import com.java.mastery.simplewebapp.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private String jobTitle;

    private LocalDate dateOfBirth;

    private Gender gender;

    private Long departmentId;
}
