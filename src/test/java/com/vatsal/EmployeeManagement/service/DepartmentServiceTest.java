package com.vatsal.EmployeeManagement.service;

import com.vatsal.EmployeeManagement.entity.Department;
import com.vatsal.EmployeeManagement.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class DepartmentServiceTest {
    @MockBean
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentService departmentService;

    @Test
    void getDepartments() {
        departmentService.getDepartments();
        verify(departmentRepository).findAll();
    }

    @Test
    void saveDepartment() {
        Department department = new Department("IT","Vatsal");
        boolean isSaved = departmentService.saveDepartment(department);
        assertThat(isSaved).isTrue();
    }
}