package com.java.mastery.simplewebapp.dao.mapper;

import com.java.mastery.simplewebapp.model.Department;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setId(rs.getLong("department_id"));
        department.setName(rs.getString("name"));

        return department;
    }
}
