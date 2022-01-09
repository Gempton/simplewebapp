package com.java.mastery.simplewebapp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeNotFoundException extends AppException {
    private static final String ERROR_MESSAGE = "error.employee_not_found";

    public EmployeeNotFoundException(Long id) {
        super(ERROR_MESSAGE, id);
    }
}
