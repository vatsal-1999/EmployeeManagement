package com.vatsal.EmployeeManagement.service;

import com.vatsal.EmployeeManagement.entity.Department;
import com.vatsal.EmployeeManagement.entity.Employee;
import com.vatsal.EmployeeManagement.repository.DepartmentRepository;
import com.vatsal.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public void saveDepartment(Department department) {
        if (department.getName().trim().equals("")) {
            throw new IllegalStateException("Department name cannot be empty");
        }
        department.setName(department.getName().toUpperCase());
        department.setHead_of_dept(department.getHead_of_dept().toUpperCase());
        Optional<Department> optionalDepartment = departmentRepository.getDepartmentByName(department.getName());
        if (optionalDepartment.isPresent()) {
            throw new IllegalStateException("Department " + department.getName() + " already exists");
        }
        departmentRepository.save(department);
    }

    @Transactional
    public void updateDepartment(Long departmentId, Department department) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isEmpty()) {
            throw new IllegalStateException("Department with id " + departmentId + " does not exists");
        }
        String name = department.getName() != null ? department.getName().toUpperCase() : "";
        Department originalDepartment = optionalDepartment.get();
        if (name.length() > 0 && !name.equals(originalDepartment.getName())) {
            if (departmentRepository.getDepartmentByName(name).isPresent()) {
                throw new IllegalStateException("Department "+name+" already exists");
            }
            originalDepartment.setName(name);
        }

        String head = department.getHead_of_dept() != null ? department.getHead_of_dept().toUpperCase() : "";
        if (head.length() > 0 && !head.equals(originalDepartment.getHead_of_dept())) {
            originalDepartment.setHead_of_dept(head);
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

    public Department getDepartment(Long departmentId) {
        Optional<Department> optional = departmentRepository.findById(departmentId);
        if (optional.isEmpty()) {
            throw new IllegalStateException("Department with id:" + departmentId + " does not exists");
        }
        return optional.get();
    }

    public List<String> getEmployeesByDepartId(Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()) {
            throw new IllegalStateException("Department with id:"+departmentId+" does not exists");
        }
        Department department = departmentOptional.get();
        List<String> namesOfEmployees = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            if (employee.getDepartments().contains(department)) {
                namesOfEmployees.add(employee.getName());
            }
        }
        return namesOfEmployees;
    }
}
