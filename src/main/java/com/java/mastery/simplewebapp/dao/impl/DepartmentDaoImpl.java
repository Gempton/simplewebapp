package com.java.mastery.simplewebapp.dao.impl;

import com.java.mastery.simplewebapp.dao.DepartmentDao;
import com.java.mastery.simplewebapp.dao.mapper.DepartmentRowMapper;
import com.java.mastery.simplewebapp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private static final String DEPARTMENT_ID = "id";
    private static final String SQL_SELECT_BY_ID_QUERY = "SELECT * FROM department where department_id=:id";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private DepartmentRowMapper departmentRowMapper;

    @Override
    public Department findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(DEPARTMENT_ID, id);
        Department department;

        try {
            department = namedParameterJdbcTemplate.queryForObject(
                    SQL_SELECT_BY_ID_QUERY,
                    parameterSource,
                    departmentRowMapper);
        } catch (DataAccessException e) {
            return null;
        }

        return department;
    }
}
