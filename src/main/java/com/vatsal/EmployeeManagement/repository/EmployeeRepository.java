package com.vatsal.EmployeeManagement.repository;

import com.vatsal.EmployeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e from Employee e where e.email = ?1")
    Optional<Employee> findEmployeeByEmail(String email);

}
