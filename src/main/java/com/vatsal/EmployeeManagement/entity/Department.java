package com.vatsal.EmployeeManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vatsal.EmployeeManagement.audit.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="departments_table")
public class Department extends Auditable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="head_of_dept")
    private String head_of_dept;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            mappedBy = "departments"
    )
    @JsonIgnore
    private List<Employee> employees = new ArrayList<>();

    public Department(){}

    public Department(String name, String head_of_dept) {
        this.name = name;
        this.head_of_dept = head_of_dept;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getHead_of_dept() {
        return head_of_dept;
    }

    public void setHead_of_dept(String head_of_dept) {
        this.head_of_dept = head_of_dept;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
