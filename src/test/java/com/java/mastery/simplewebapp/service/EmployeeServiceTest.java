package com.java.mastery.simplewebapp.service;

import com.java.mastery.simplewebapp.dao.DepartmentDao;
import com.java.mastery.simplewebapp.dao.EmployeeDao;
import com.java.mastery.simplewebapp.exception.DepartmentNotFoundException;
import com.java.mastery.simplewebapp.exception.EmployeeNotFoundException;
import com.java.mastery.simplewebapp.model.Department;
import com.java.mastery.simplewebapp.model.Employee;
import com.java.mastery.simplewebapp.model.enums.Gender;
import com.java.mastery.simplewebapp.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private static final String EMPLOYEE_NOT_FOUND_ERROR = "error.employee_not_found";
    private static final String DEPARTMENT_NOT_FOUND_ERROR = "error.department_not_found";

    @Mock
    private DepartmentDao departmentDao;

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    private Employee employeeTest;
    private Employee employeeUpdated;
    private List<Employee> employees = new ArrayList<>();

    @BeforeEach
    private void init() {
        employeeTest = Employee.builder()
                .id(1L)
                .firstName("Ilya")
                .lastName("Petrov")
                .jobTitle("Programmer")
                .dateOfBirth(LocalDate.of(2004, 11, 14))
                .gender(Gender.MALE)
                .departmentId(3L)
                .build();

        employeeUpdated = Employee.builder()
                .id(1L)
                .firstName("Ilya")
                .lastName("Petrov")
                .jobTitle("Programmer")
                .dateOfBirth(LocalDate.of(2004, 11, 14))
                .gender(Gender.FEMALE)
                .departmentId(4L)
                .build();

        employees.add(employeeTest);
        employees.add(employeeUpdated);
    }

    @Test
    public void shouldCreateEmployee() {
        when(employeeDao.create(employeeTest)).thenReturn(employeeTest);
        when(departmentDao.findById(employeeTest.getDepartmentId())).thenReturn(new Department());

        employeeServiceImpl.create(employeeTest);

        verify(employeeDao, times(1)).create(employeeTest);
    }

    @Test
    public void createEmployeeShouldThrowDepartmentNotFoundException() {
        when(departmentDao.findById(employeeTest.getDepartmentId())).thenThrow(new DepartmentNotFoundException(employeeTest.getDepartmentId()));

        DepartmentNotFoundException thrown = assertThrows(DepartmentNotFoundException.class, () -> {
            employeeServiceImpl.create(employeeTest);
        });

        assertEquals(DEPARTMENT_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void findByIdShouldFindEmployee() {
        when(employeeDao.findById(1L)).thenReturn(employeeTest);

        Employee receivedEmployee = employeeServiceImpl.findById(1L);

        assertNotNull(employeeTest);
        assertEquals("Ilya", receivedEmployee.getFirstName());
        assertEquals("Petrov", receivedEmployee.getLastName());
        assertEquals("Programmer", receivedEmployee.getJobTitle());
        assertEquals(LocalDate.of(2004, 11, 14), receivedEmployee.getDateOfBirth());
        assertEquals(Gender.MALE, receivedEmployee.getGender());
        assertEquals(3L, receivedEmployee.getDepartmentId());
    }

    @Test
    public void findByIdShouldThrowEmployeeNotFoundException() {
        when(employeeDao.findById(10L)).thenThrow(new EmployeeNotFoundException(10L));

        EmployeeNotFoundException thrown = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeServiceImpl.findById(10L);
        });

        assertEquals(EMPLOYEE_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void updateEmployeeShouldThrowDepartmentNotFoundException() {
        when(departmentDao.findById(employeeTest.getDepartmentId())).thenReturn(null);

        DepartmentNotFoundException thrown = assertThrows(DepartmentNotFoundException.class, () -> {
            employeeServiceImpl.update(employeeTest);
        });

        assertEquals(DEPARTMENT_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void updateEmployeeShouldThrowEmployeeNotFoundException() {
        when(employeeDao.update(employeeTest)).thenThrow(new EmployeeNotFoundException(employeeTest.getId()));
        when(departmentDao.findById(employeeTest.getDepartmentId())).thenReturn(new Department());

        EmployeeNotFoundException thrown = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeServiceImpl.update(employeeTest);
        });

        assertEquals(EMPLOYEE_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void shouldUpdateEmployee() {
        when(employeeDao.update(employeeTest)).thenReturn(employeeUpdated);
        when(departmentDao.findById(employeeTest.getDepartmentId())).thenReturn(new Department());

        Employee receivedEmployee = employeeServiceImpl.update(employeeTest);

        assertNotNull(employeeUpdated);
        assertEquals(employeeUpdated.getFirstName(), receivedEmployee.getFirstName());
        assertEquals(employeeUpdated.getLastName(), receivedEmployee.getLastName());
        assertEquals(employeeUpdated.getJobTitle(), receivedEmployee.getJobTitle());
        assertEquals(employeeUpdated.getDateOfBirth(), receivedEmployee.getDateOfBirth());
        assertEquals(employeeUpdated.getGender(), receivedEmployee.getGender());
        assertEquals(employeeUpdated.getDepartmentId(), receivedEmployee.getDepartmentId());
    }

    @Test
    public void shouldDeleteById() {
        employeeServiceImpl.deleteById(1L);

        verify(employeeDao, times(1)).deleteById(1L);
    }

    @Test
    public void shouldFindAllEmployees() {
        when(employeeDao.findAll()).thenReturn(employees);

        List<Employee> receivedEmployees = employeeServiceImpl.findAll();

        assertFalse(receivedEmployees.isEmpty());
        assertEquals(employees, receivedEmployees);
    }
}