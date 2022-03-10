package com.vatsal.EmployeeManagement;

import com.vatsal.EmployeeManagement.entity.Department;
import com.vatsal.EmployeeManagement.entity.Employee;
import com.vatsal.EmployeeManagement.repository.DepartmentRepository;
import com.vatsal.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class EmployeeManagementApplication implements CommandLineRunner {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Department department1 = new Department("IT");
		Department department2 = new Department("HR");

		Employee employee1 = new Employee("Vatsal Aggarwal",
				"7888486819",
				LocalDate.of(1999, Month.DECEMBER,11),
				"aggarwal.vatsal5@gmail.com");
		Employee employee2 = new Employee("Harbilas Singh",
				"123456789",
				LocalDate.of(1999, Month.JANUARY,10),
				"singh.harbilas2000@gmail.com");

		department1.getEmployees().add(employee1);
		department2.getEmployees().add(employee2);

		employee1.getDepartments().add(department1);
		employee2.getDepartments().add(department2);

		employeeRepository.saveAll(List.of(employee1,employee2));
	}
}
