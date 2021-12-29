package com.java.mastery.simplewebapp.dao.impl;

import com.java.mastery.simplewebapp.dao.EmployeeDao;
import com.java.mastery.simplewebapp.dao.mapper.EmployeeRowMapper;
import com.java.mastery.simplewebapp.dao.mapper.EmployeeMapper;
import com.java.mastery.simplewebapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private static final String SQL_EMPLOYEE_ID = "employee_id";
    private static final String EMPLOYEE_ID = "id";
    private static final String SQL_INSERT_QUERY = "INSERT INTO employee " +
            "(FIRST_NAME, LAST_NAME, JOB_TITLE, GENDER, DATE_OF_BIRTH, DEPARTMENT_ID)" +
            "VALUES (:firstName, :lastName, :jobTitle, :gender, :dateOfBirth, :departmentId)";
    private static final String SQL_SELECT_QUERY_BY_ID = "SELECT * FROM employee WHERE employee_id=:id";
    private static final String SQL_UPDATE_QUERY = "UPDATE employee SET first_name=:firstName, last_name=:lastName, job_title=:jobTitle, gender=:gender, date_of_birth=:dateOfBirth, department_id=:departmentId WHERE employee_id=:id";
    private static final String SQL_DELETE_QUERY = "DELETE FROM employee WHERE employee_id=:id";
    private static final String SQL_SELECT_ALL_QUERY = "SELECT * FROM employee";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee create(Employee employee) {
        KeyHolder holder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(
                SQL_INSERT_QUERY,
                employeeMapper.employeeToMap(employee),
                holder);

        employee.setId(Long.parseLong(holder.getKeys().get(SQL_EMPLOYEE_ID).toString()));
        return employee;
    }

    @Override
    public Employee findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(EMPLOYEE_ID, id);
        Employee employee;

        try {
            employee = namedParameterJdbcTemplate.queryForObject(
                    SQL_SELECT_QUERY_BY_ID,
                    parameterSource,
                    new EmployeeRowMapper());
        } catch (DataAccessException e) {
            return null;
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        namedParameterJdbcTemplate.update(SQL_UPDATE_QUERY, employeeMapper.employeeToMap(employee));

        return findById(employee.getId());
    }

    @Override
    public int deleteById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(EMPLOYEE_ID, id);

        return namedParameterJdbcTemplate.update(SQL_DELETE_QUERY, parameterSource);
    }

    @Override
    public List<Employee> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_QUERY, new EmployeeRowMapper());
    }
}
