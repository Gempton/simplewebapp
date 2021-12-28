package com.java.mastery.simplewebapp.dao;

import com.java.mastery.simplewebapp.model.Department;

public interface DepartmentDao {

    Department findById(Long id);
}
