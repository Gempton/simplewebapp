package com.java.mastery.simplewebapp.service.impl;

import com.java.mastery.simplewebapp.dao.DepartmentDao;
import com.java.mastery.simplewebapp.dao.EmployeeDao;
import com.java.mastery.simplewebapp.exception.DepartmentNotFoundException;
import com.java.mastery.simplewebapp.exception.EmployeeNotFoundException;
import com.java.mastery.simplewebapp.model.Department;
import com.java.mastery.simplewebapp.model.Employee;
import com.java.mastery.simplewebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Employee create(Employee employee) {
        Department department = departmentDao.findById(employee.getDepartmentId());
        if (department == null) {
            throw new DepartmentNotFoundException(employee.getDepartmentId());
        }

        return employeeDao.create(employee);
    }

    @Override
    public Employee update(Employee employee) {
        Department department = departmentDao.findById(employee.getDepartmentId());
        if (department == null) {
            throw new DepartmentNotFoundException(employee.getDepartmentId());
        }

        return employeeDao.update(employee);
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = employeeDao.findById(id);

        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }

        return employee;
    }

    @Override
    public void deleteById(Long id) {
        employeeDao.deleteById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}
