package com.vatsal.EmployeeManagement.service;

import com.vatsal.EmployeeManagement.dto.EmployeeDTO;
import com.vatsal.EmployeeManagement.entity.Employee;
import com.vatsal.EmployeeManagement.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void getEmployees() {
        employeeService.getEmployees();
        verify(employeeRepository).findAll();
    }

    @Test
    void saveEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO("Vatsal","1234567890", LocalDate.of(1999,12,11),"aggarwal.vatsal5@gmail.com");
        boolean isSaved = employeeService.saveEmployee(employeeDTO);
        assertThat(isSaved).isTrue();
    }

    @Test
    void updateEmployee() {
//        EmployeeDTO employeeDTO = new EmployeeDTO("Vatsal","1234567890", LocalDate.of(1999,12,11),"aggarwal.vatsal5@gmail.com");
//        System.out.println(employeeService.saveEmployee(employeeDTO));
//        employeeDTO.setPhoneNumber("7888486819");
//        boolean isUpdated = employeeService.updateEmployee(1L,employeeDTO);
//        assertThat(isUpdated).isTrue();
//        Mockito.when(employeeService.updateEmployee(1L,employeeDTO)).
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void getEmployee() {

    }
}