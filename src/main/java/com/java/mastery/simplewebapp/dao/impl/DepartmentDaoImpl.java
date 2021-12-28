package com.java.mastery.simplewebapp.dao.impl;

import com.java.mastery.simplewebapp.dao.DepartmentDao;
import com.java.mastery.simplewebapp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Department findById(Long id) {
        Department department;
        try {
            department = jdbcTemplate.queryForObject("SELECT * FROM department where department_id=?",
                    BeanPropertyRowMapper.newInstance(Department.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return department;
    }
}
