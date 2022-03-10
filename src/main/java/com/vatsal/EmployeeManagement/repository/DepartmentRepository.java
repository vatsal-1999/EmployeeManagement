package com.vatsal.EmployeeManagement.repository;

import com.vatsal.EmployeeManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query("SELECT d from Department d WHERE d.name = ?1")
    Optional<Department> getDepartmentByName(String name);
}
