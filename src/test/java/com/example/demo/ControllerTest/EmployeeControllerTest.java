package com.example.demo.ControllerTest;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmployeeRepository employeeRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        employeeRepository.deleteAll();

        Employee emp1 = new Employee(1L, "John Doe", "HR", null);
        Employee emp2 = new Employee(2L, "Jane Smith", "IT", null);
        employeeRepository.saveAll(List.of(emp1, emp2));
    }

    @AfterEach
    public void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void store_Valid_Created() throws Exception {
        Employee newEmployee = new Employee(1L, "Alice Johnson", "Finance", null);
        String body = objectMapper.writeValueAsString(newEmployee);

        MvcResult mvcResult = mockMvc.perform(post("/employees")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Alice Johnson"));
    }


    @Test
    public void getById_NotFound() throws Exception {
        mockMvc.perform(get("/employees/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void update_NotFound() throws Exception {
        Employee updatedEmployee = new Employee(999999L, "Updated Name", "Updated Dept", null);
        String body = objectMapper.writeValueAsString(updatedEmployee);

        mockMvc.perform(put("/employees/999999")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}