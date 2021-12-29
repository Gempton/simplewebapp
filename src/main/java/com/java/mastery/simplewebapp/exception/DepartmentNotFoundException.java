package com.java.mastery.simplewebapp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentNotFoundException extends RuntimeException{
    private static final String ERROR_MESSAGE = "error.department_not_found";

    private Long id;

    public DepartmentNotFoundException(Long id) {
        super(ERROR_MESSAGE);
        this.id = id;
    }
}
