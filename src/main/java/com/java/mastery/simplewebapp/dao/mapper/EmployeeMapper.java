package com.java.mastery.simplewebapp.dao.mapper;

import com.java.mastery.simplewebapp.model.Employee;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String JOB_TITLE = "jobTitle";
    private static final String GENDER = "gender";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String DEPARTMENT_ID = "departmentId";
    private static final String EMPLOYEE_ID = "id";

    public SqlParameterSource employeeToMap(Employee employee) {

        return new MapSqlParameterSource()
                .addValue(FIRST_NAME, employee.getFirstName())
                .addValue(LAST_NAME, employee.getLastName())
                .addValue(JOB_TITLE, employee.getJobTitle())
                .addValue(GENDER, employee.getGender().name())
                .addValue(DATE_OF_BIRTH, employee.getDateOfBirth())
                .addValue(DEPARTMENT_ID, employee.getDepartmentId())
                .addValue(EMPLOYEE_ID, employee.getId());
    }
}
