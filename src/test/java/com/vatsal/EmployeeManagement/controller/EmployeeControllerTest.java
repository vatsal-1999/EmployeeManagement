package com.vatsal.EmployeeManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vatsal.EmployeeManagement.entity.Employee;
import com.vatsal.EmployeeManagement.repository.DepartmentRepository;
import com.vatsal.EmployeeManagement.repository.EmployeeRepository;
import com.vatsal.EmployeeManagement.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getEmployees() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }

    @Test
    void getEmployee() throws Exception {
        mockMvc.perform(get("/employees/{employeeId}",1L)).andExpect(status().isOk());
    }

    @Test
    void saveEmployee() throws Exception {
        String json = "{\n" +
                "  \"dob\": \"1999-12-11\",\n" +
                "  \"email\": \"vatsal@gmail.com\",\n" +
                "  \"name\": \"Vatsal\",\n" +
                "  \"phoneNumber\": \"7888486819\"\n" +
                "}";
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    void updateEmployee() throws Exception{
        String json = "{\n" +
                "  \"dob\": \"1999-12-11\",\n" +
                "  \"email\": \"vatsal@gmail.com\",\n" +
                "  \"name\": \"Vatsal\",\n" +
                "  \"phoneNumber\": \"7888486819\"\n" +
                "}";
        mockMvc.perform(put("/employees/update/{employeeId}",1L).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    void deleteEmployee() throws Exception {
        mockMvc.perform(delete("/employees/delete/{employeeId}",1L)).andExpect(status().isOk());
    }
}