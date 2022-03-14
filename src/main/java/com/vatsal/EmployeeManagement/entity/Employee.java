package com.vatsal.EmployeeManagement.entity;

import com.vatsal.EmployeeManagement.audit.Auditable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employees_table")
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="phone_num")
    private String phoneNumber;

    @Column(name="dob")
    private LocalDate dob;

    @Column(name="email")
    private String email;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    @JoinTable(
            name="employee_department",
            joinColumns = {
                    @JoinColumn(name="emp_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="dep_id")
            }
    )
    private List<Department> departments = new ArrayList<>();

    public Employee(){}

    public Employee(String name, String phoneNumber, LocalDate dob, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", departments=" + departments +
                '}';
    }
}
