package com.vatsal.EmployeeManagement.repository;

import com.vatsal.EmployeeManagement.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void getDepartmentByName() {
        Department department =new Department("IT","Vatsal");
        departmentRepository.save(department);
        assertThat(departmentRepository.getDepartmentByName("IT").get().getHead_of_dept()).isEqualTo("Vatsal");
    }
}