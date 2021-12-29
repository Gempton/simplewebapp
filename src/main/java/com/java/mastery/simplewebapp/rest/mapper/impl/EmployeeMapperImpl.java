package com.java.mastery.simplewebapp.rest.mapper.impl;

import com.java.mastery.simplewebapp.dto.EmployeeDto;
import com.java.mastery.simplewebapp.model.Employee;
import com.java.mastery.simplewebapp.rest.mapper.EmployeeMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    public Employee mapToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setJobTitle(employeeDto.getJobTitle());
        employee.setGender(employeeDto.getGender());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setDepartmentId(employeeDto.getDepartmentId());
        return employee;
    }

    public EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setJobTitle(employee.getJobTitle());
        employeeDto.setGender(employee.getGender());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setDepartmentId(employee.getDepartmentId());
        return employeeDto;
    }
}
