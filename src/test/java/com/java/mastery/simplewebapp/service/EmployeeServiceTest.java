package com.java.mastery.simplewebapp.service;

import com.java.mastery.simplewebapp.exception.DepartmentNotFoundException;
import com.java.mastery.simplewebapp.exception.EmployeeNotFoundException;
import com.java.mastery.simplewebapp.model.Employee;
import com.java.mastery.simplewebapp.model.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class EmployeeServiceTest {

    private static final String EMPLOYEE_NOT_FOUND_ERROR = "error.employee_not_found";
    private static final String DEPARTMENT_NOT_FOUND_ERROR = "error.department_not_found";
    private static final Long EMPLOYEE_AND_DEPARTMENT_ID_FOR_TEST_NOT_FOUND_EXCEPTION = 100000L;

    @Autowired
    private EmployeeService employeeService;

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
    public void shouldCreateEmployeeTest() {
        Employee created = employeeService.create(employeeTest);

        Assertions.assertNotNull(created);
        Assertions.assertEquals(created.getFirstName(), employeeTest.getFirstName());
        Assertions.assertEquals(created.getLastName(), employeeTest.getLastName());
    }

    @Test
    public void shouldFindByIdThrowDepartmentNotFoundExceptionTest() {
        employeeTest.setDepartmentId(EMPLOYEE_AND_DEPARTMENT_ID_FOR_TEST_NOT_FOUND_EXCEPTION);

        DepartmentNotFoundException thrown = Assertions.assertThrows(DepartmentNotFoundException.class, () -> {
            employeeService.create(employeeTest);
        });

        Assertions.assertEquals(DEPARTMENT_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void shouldFindByIdThrowEmployeeNotFoundTest() {
        EmployeeNotFoundException thrown = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findById(EMPLOYEE_AND_DEPARTMENT_ID_FOR_TEST_NOT_FOUND_EXCEPTION);
        });

        Assertions.assertEquals(EMPLOYEE_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void shouldFindByIdTest() {
        Employee employee = employeeService.findById(1L);
        Assertions.assertNotNull(employee);
    }

    @Test
    public void shouldUpdateThrowDepartmentNotFoundTest() {
        employeeTest.setDepartmentId(EMPLOYEE_AND_DEPARTMENT_ID_FOR_TEST_NOT_FOUND_EXCEPTION);
        DepartmentNotFoundException thrown = Assertions.assertThrows(DepartmentNotFoundException.class, () -> {
            employeeService.update(employeeTest);
        });

        Assertions.assertEquals(DEPARTMENT_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void shouldUpdateTest() {
        employeeTest.setId(2L);
        employeeTest.setDepartmentId(4L);
        employeeTest.setGender(Gender.FEMALE);
        Employee employee = employeeService.update(employeeTest);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(employeeTest.getGender().name(), employee.getGender().name());
        Assertions.assertEquals(employeeTest.getDepartmentId(), employee.getDepartmentId());
    }

    @Test
    public void shouldDeleteByIdTest() {
        Long employeeId = 3L;
        employeeService.deleteById(employeeId);
        EmployeeNotFoundException thrown = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findById(employeeId);
        });
    }

    @Test
    public void shouldGetAllEmployeesTest() {
        List<Employee> employees = employeeService.findAll();
        Assertions.assertFalse(employees.isEmpty());
    }
}