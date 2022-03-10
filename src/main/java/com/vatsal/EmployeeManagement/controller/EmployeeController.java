package com.vatsal.EmployeeManagement.controller;

import com.vatsal.EmployeeManagement.dto.EmployeeDTO;
import com.vatsal.EmployeeManagement.entity.Employee;
import com.vatsal.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path="employees"
)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping
    public void saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.saveEmployee(employeeDTO);
    }

    @PutMapping(path = "update/{employeeId}")
    public void updateEmployee(@PathVariable Long employeeId,
                                @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(employeeId,employeeDTO);
    }
}
