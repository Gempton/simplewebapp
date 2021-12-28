package com.java.mastery.simplewebapp.dao.mapper;

import com.java.mastery.simplewebapp.model.Employee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeToMap {

    public Map<String, Object> toMap(Employee employee) {
        Map<String, Object> map = new HashMap<>();
        map.put("first_name", employee.getFirstName());
        map.put("last_name", employee.getLastName());
        map.put("job_title", employee.getJobTitle());
        map.put("gender", employee.getGender().name());
        map.put("date_of_birth", employee.getDateOfBirth());
        map.put("department_id", employee.getDepartmentId());

        return map;
    }
}
