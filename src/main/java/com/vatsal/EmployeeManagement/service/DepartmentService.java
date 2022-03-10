package com.vatsal.EmployeeManagement.service;

import com.vatsal.EmployeeManagement.entity.Department;
import com.vatsal.EmployeeManagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public void saveDepartment(Department department) {
        if (department.getName().trim().equals("")) {
            throw new IllegalStateException("Department name cannot be empty");
        }
        department.setName(department.getName().toUpperCase());
        Optional<Department> optionalDepartment = departmentRepository.getDepartmentByName(department.getName());
        if (optionalDepartment.isPresent()) {
            throw new IllegalStateException("Department " + department.getName() + " already exists");
        }
        departmentRepository.save(department);
    }

    @Transactional
    public void updateDepartment(Long departmentId, String name) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isEmpty()) {
            throw new IllegalStateException("Department with id " + departmentId + " does not exists");
        }
        name = name.toUpperCase();
        Department department = optionalDepartment.get();
        if (name.length() > 0 && !name.equals(department.getName())) {
            if (departmentRepository.getDepartmentByName(name).isPresent()) {
                throw new IllegalStateException("Department "+name+" already exists");
            }
            department.setName(name);
        } else {
            throw new IllegalStateException("Department name is invalid");
        }
    }

    public void deleteDepartment(Long departmentId) {
        try {
            Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
            if (optionalDepartment.isEmpty()) {
                throw new IllegalStateException("Department with id " + departmentId + " does not exists");
            }
            departmentRepository.delete(optionalDepartment.get());
        } catch (Exception e) {
            throw new IllegalStateException("There are records related to this department in employees table");
        }
    }
}
