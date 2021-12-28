package com.java.mastery.simplewebapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Department {
    private Long departmentId;
    private String name;

    public Department(Long departmentId) {
        this.departmentId = departmentId;
    }
}
