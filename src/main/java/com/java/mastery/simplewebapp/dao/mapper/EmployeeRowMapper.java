package com.java.mastery.simplewebapp.dao.mapper;

import com.java.mastery.simplewebapp.model.Employee;
import com.java.mastery.simplewebapp.model.enums.Gender;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getLong("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setJobTitle("job_title");
        employee.setGender(Gender.valueOf(rs.getString("gender")));
        employee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        employee.setDepartmentId(rs.getLong("department_id"));

        return employee;
    }
}
