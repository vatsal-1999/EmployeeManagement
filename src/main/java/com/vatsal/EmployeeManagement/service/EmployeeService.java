package com.vatsal.EmployeeManagement.service;

import com.vatsal.EmployeeManagement.dto.EmployeeDTO;
import com.vatsal.EmployeeManagement.entity.Department;
import com.vatsal.EmployeeManagement.entity.Employee;
import com.vatsal.EmployeeManagement.repository.DepartmentRepository;
import com.vatsal.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(EmployeeDTO employeeDTO) {

        if (employeeDTO.getName().equals("") ||
            employeeDTO.getPhoneNumber().equals("") ||
            employeeDTO.getDob().toString().equals("") ||
            employeeDTO.getEmail().equals("")) {
            throw new IllegalArgumentException("One of parameters is Empty");
        }

        Employee employee = new Employee(employeeDTO.getName(),
                employeeDTO.getPhoneNumber(),
                employeeDTO.getDob(),
                employeeDTO.getEmail());

        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if (optionalEmployee.isPresent()) {
            throw new IllegalStateException("Employee with email ID: " + employee.getEmail() + " already exists");
        }
        for (long id : employeeDTO.getDepartmentIds()) {
            Optional<Department> optional = departmentRepository.findById(id);
            if (optional.isEmpty()) {
                throw new IllegalStateException("Department with id:" + id + " does not exists");
            }
            Department department = optional.get();
            employee.getDepartments().add(department);
            department.getEmployees().add(employee);
        }
        employeeRepository.save(employee);
    }

    @Transactional
    public void updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new IllegalStateException("No Employee with id:" + employeeId + " does not exists");
        }
        Employee employee = optionalEmployee.get();
        if (employeeDTO.getName() !=  null && employeeDTO.getName().length() > 0) {
            employee.setName(employeeDTO.getName());
        }
        if (employeeDTO.getPhoneNumber() != null && employeeDTO.getPhoneNumber().length() > 0) {
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        }
        if (employeeDTO.getEmail() != null && employeeDTO.getEmail().length() > 0) {
            employee.setEmail(employeeDTO.getEmail());
        }
        if (employeeDTO.getDob() != null && employeeDTO.getDob().toString().length() > 0) {
            employee.setDob(employeeDTO.getDob());
        }
        if (!employeeDTO.getDepartmentIds().isEmpty()) {
            employee.getDepartments().clear();
            for (long id : employeeDTO.getDepartmentIds()) {
                Optional<Department> optional = departmentRepository.findById(id);
                if (optional.isEmpty()) {
                    throw new IllegalStateException("Department with id:" + id + " does not exists");
                }
                Department department = optional.get();
                employee.getDepartments().add(department);
                department.getEmployees().add(employee);
            }
        }
    }

    public void deleteEmployee(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new IllegalStateException("Employee with id:" + employeeId + " does not exists");
        }
        employeeRepository.deleteById(employeeId);
    }

    public Employee getEmployee(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            throw new IllegalStateException("Employee with id:"+ employeeId + " does not exists");
        }
        return employeeOptional.get();
    }
}
