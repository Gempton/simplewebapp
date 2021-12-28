package com.java.mastery.simplewebapp.service;

import com.java.mastery.simplewebapp.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee findById(Long id);

    Employee update(Employee employee);

    int deleteById(Long id);

    List<Employee> findAll();
}
