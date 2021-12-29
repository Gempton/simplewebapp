package com.java.mastery.simplewebapp.rest;

import com.java.mastery.simplewebapp.dto.EmployeeDto;
import com.java.mastery.simplewebapp.model.Employee;
import com.java.mastery.simplewebapp.rest.mapper.impl.EmployeeMapperImpl;
import com.java.mastery.simplewebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapperImpl employeeMapper;

    @PostMapping("/employee")
    public EmployeeDto add(@RequestBody @Valid EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapToEmployee(employeeDto);
        Employee newEmployee = employeeService.create(employee);
        return employeeMapper.mapToEmployeeDto(newEmployee);
    }

    @GetMapping("/employee/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return employeeMapper.mapToEmployeeDto(employee);
    }

    @PutMapping("/employee/{id}")
    public EmployeeDto update(@PathVariable Long id, @RequestBody @Valid EmployeeDto employeeDto) {
        Employee newEmployee = employeeMapper.mapToEmployee(employeeDto);
        newEmployee.setId(id);
        employeeService.update(newEmployee);
        return employeeMapper.mapToEmployeeDto(newEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public Long delete(@PathVariable Long id) {
        employeeService.findById(id);
        employeeService.deleteById(id);
        return id;
    }

    @GetMapping("/employee")
    public List<EmployeeDto> findAll() {
        List<Employee> list = employeeService.findAll();
        return list.stream().map(employee -> employeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }
}
