package com.java.mastery.simplewebapp.service.impl;

import com.java.mastery.simplewebapp.dao.impl.DepartmentDaoImpl;
import com.java.mastery.simplewebapp.dao.impl.EmployeeDaoImpl;
import com.java.mastery.simplewebapp.exception.department.NoSuchDepartmentException;
import com.java.mastery.simplewebapp.exception.employee.NoSuchEmployeeException;
import com.java.mastery.simplewebapp.model.Department;
import com.java.mastery.simplewebapp.model.Employee;
import com.java.mastery.simplewebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDaoImpl employeeDao;

    @Autowired
    private DepartmentDaoImpl departmentDao;

    @Override
    public Employee save(Employee employee) {
        Department department = departmentDao.findById(employee.getDepartmentId());
        if (department == null) {
            throw new NoSuchDepartmentException("There is no Department with ID = "  + employee.getDepartmentId());
        }

        return employeeDao.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        Department department = departmentDao.findById(employee.getDepartmentId());
        if (department == null) {
            throw new NoSuchDepartmentException("There is no Department with ID = "  + employee.getDepartmentId());
        }

        return employeeDao.update(employee);
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = employeeDao.findById(id);

        if (employee == null) {
            throw new NoSuchEmployeeException("There is no Employee with ID = " + id);
        }

        return employee;
    }

    @Override
    public int deleteById(Long id) {
        return employeeDao.deleteById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}
