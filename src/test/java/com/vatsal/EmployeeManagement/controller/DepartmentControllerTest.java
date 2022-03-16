package com.vatsal.EmployeeManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vatsal.EmployeeManagement.repository.DepartmentRepository;
import com.vatsal.EmployeeManagement.repository.EmployeeRepository;
import com.vatsal.EmployeeManagement.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DepartmentRepository departmentRepository;

    @MockBean
    DepartmentService departmentService;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    void getDepartments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments")).andExpect(status().isOk());
    }

    @Test
    void getDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/{departmentId}",1L)).andExpect(status().isOk());
    }

    @Test
    void getEmployeesByDepartId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/findEmployees/{departmentId}",1L)).andExpect(status().isOk());
    }

    @Test
    void saveDepartment() throws Exception {
        String json = "{\"head_of_dept\": \"string\",\n" +
                "  \"name\": \"string\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/departments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    void updateDepartment() throws Exception {
        String json = "{\"head_of_dept\": \"string\",\n" +
                "  \"name\": \"string\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.put("/departments/update/{departmentId}",1L).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    void deleteDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/delete/{departmentId}",1L)).andExpect(status().isOk());
    }
}