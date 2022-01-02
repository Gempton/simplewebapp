package com.java.mastery.simplewebapp.dao;

import com.java.mastery.simplewebapp.model.Employee;
import com.java.mastery.simplewebapp.model.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    private Employee employeeTest;

    @BeforeEach
    private void init() {
        employeeTest = Employee.builder()
                .firstName("Ilya")
                .lastName("Petrov")
                .jobTitle("programmer")
                .dateOfBirth(LocalDate.of(2004, 11, 14))
                .gender(Gender.MALE)
                .departmentId(3L)
                .build();
    }

    @Test
    public void shouldCreateTest() {
        Assertions.assertNotNull(employeeDao.create(employeeTest));
    }

    @Test
    public void shouldUpdateTest() {
        employeeTest.setId(employeeDao.create(employeeTest).getId());
        employeeTest.setGender(Gender.FEMALE);
        employeeTest.setDepartmentId(2L);

        Employee employee = employeeDao.update(employeeTest);

        Assertions.assertNotNull(employee);
        Assertions.assertEquals(employeeTest.getGender(), employee.getGender());
        Assertions.assertEquals(employeeTest.getDepartmentId(), employee.getDepartmentId());
        Assertions.assertEquals(employeeTest, employee);
    }

    @Test
    public void shouldFinDByIdTest() {
        Assertions.assertNotNull(employeeDao.findById(1L));
    }

    @Test
    public void shouldFindAllTest() {
        Assertions.assertNotNull(employeeDao.findAll());
    }

    @Test
    public void shouldDeleteTest() {
        employeeDao.deleteById(3L);
        Assertions.assertNull(employeeDao.findById(3L));
    }


}
