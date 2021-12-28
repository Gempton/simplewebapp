package com.java.mastery.simplewebapp.dao.impl;

import com.java.mastery.simplewebapp.dao.EmployeeDao;
import com.java.mastery.simplewebapp.dao.mapper.EmployeeRowMapper;
import com.java.mastery.simplewebapp.dao.mapper.EmployeeToMap;
import com.java.mastery.simplewebapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmployeeToMap employeeToMap;

    @Override
    public Employee save(Employee employee) {
        Number id = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("employee")
                .usingColumns("first_name", "last_name", "job_title", "gender", "date_of_birth", "department_id")
                .usingGeneratedKeyColumns("employee_id")
                .executeAndReturnKey(employeeToMap.toMap(employee));

        System.out.println(id);

        return employee;
    }

    @Override
    public Employee findById(Long id) {
        Employee employee;

        try {
            employee = jdbcTemplate.queryForObject("SELECT * FROM employee WHERE employee_id=?",
                    new EmployeeRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        jdbcTemplate.update("UPDATE employee SET first_name=?, last_name=?, job_title=?, gender=?, date_of_birth=?, department_id=? WHERE employee_id=?",
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getGender().name(),
                employee.getDateOfBirth(), employee.getDepartmentId(), employee.getEmployeeId());

        return findById(employee.getEmployeeId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM employee WHERE employee_id=?", id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee",
                new EmployeeRowMapper());
    }
}
