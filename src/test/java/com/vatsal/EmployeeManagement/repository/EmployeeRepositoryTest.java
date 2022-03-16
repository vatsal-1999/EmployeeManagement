package com.vatsal.EmployeeManagement.repository;

import com.vatsal.EmployeeManagement.entity.Department;
import com.vatsal.EmployeeManagement.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void findEmployeeByEmail() {
        Employee employee = new Employee("Vatsal","7888486819", LocalDate.of(1999,12,11),"aggarwal.vatsal5@gmail.com");
        employeeRepository.save(employee);
        assertThat(employeeRepository.findEmployeeByEmail("aggarwal.vatsal5@gmail.com").get().getName()).isEqualTo("Vatsal");
    }
}