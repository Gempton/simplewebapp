package com.java.mastery.simplewebapp.rest;

import com.java.mastery.simplewebapp.dto.EmployeeDto;
import com.java.mastery.simplewebapp.model.Employee;
import com.java.mastery.simplewebapp.rest.mapper.EmployeeMapper;
import com.java.mastery.simplewebapp.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping("/employee")
    public EmployeeDto add(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapToEmployee(employeeDto);
        Employee newEmployee = employeeService.save(employee);
        return employeeMapper.mapToEmployeeDto(newEmployee);
    }

    @GetMapping("/employee/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return employeeMapper.mapToEmployeeDto(employee);
    }

    @PutMapping("/employee/{id}")
    public EmployeeDto update(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee newEmployee = employeeMapper.mapToEmployee(employeeDto);
        newEmployee.setEmployeeId(id);
        employeeService.update(newEmployee);
        return employeeMapper.mapToEmployeeDto(newEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public Long delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return id;
    }

    @GetMapping("/employee")
    public List<EmployeeDto> findAll() {
        List<Employee> list = employeeService.findAll();
        return list.stream().map(employee -> employeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }
}
