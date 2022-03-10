package com.vatsal.EmployeeManagement.controller;

import com.vatsal.EmployeeManagement.entity.Department;
import com.vatsal.EmployeeManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path="departments"
)
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping
    public void saveDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);
    }

    @PutMapping(path = "update/{departmentId}")
    public void updateDepartment(@PathVariable("departmentId") Long departmentId,
                                 @RequestParam String name) {
        departmentService.updateDepartment(departmentId, name);
    }

    @DeleteMapping(path = "delete/{departmentId}")
    public void deleteDepartment(@PathVariable("departmentId") Long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }
}
