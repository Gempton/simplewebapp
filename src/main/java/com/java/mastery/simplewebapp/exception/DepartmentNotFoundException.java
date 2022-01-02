package com.java.mastery.simplewebapp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentNotFoundException extends AppException {
    private static final String ERROR_MESSAGE = "error.department_not_found";

    public DepartmentNotFoundException(Object data) {
        super(ERROR_MESSAGE, data);
    }
}
