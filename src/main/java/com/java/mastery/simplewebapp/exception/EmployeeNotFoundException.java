package com.java.mastery.simplewebapp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeNotFoundException extends RuntimeException{
    private static final String ERROR_MESSAGE = "error.employee_not_found";

    private Long id;

    public EmployeeNotFoundException(Long id) {
        super(ERROR_MESSAGE);
        this.id = id;
    }
}
