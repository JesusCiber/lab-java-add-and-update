package com.example.demo.controller;


import com.example.demo.DTO.EmployeeDepartmentDTO;
import com.example.demo.DTO.EmployeeStatusDTO;
import com.example.demo.EmployeeStatus;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getPatientById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    @GetMapping(params = "status")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getPatientsByStatus(@RequestParam EmployeeStatus status) {
        return employeeRepository.findByStatus(status);
    }

    @GetMapping(params = "department")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getPatientsByDepartment(@RequestParam String department) {
        return employeeRepository.findByDepartment(department);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployeeStatus(@PathVariable Long id, @RequestBody EmployeeStatusDTO employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        if (employee.getStatus() != null) {
            existingEmployee.setStatus(employee.getStatus());
        }
        return employeeRepository.save(existingEmployee);
    }

    @PatchMapping("/{id}/department")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployeeDepartment(@PathVariable Long id, @RequestBody EmployeeDepartmentDTO employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));

        if (employee.getDepartment() != null) {
            existingEmployee.setDepartment(employee.getDepartment());
        }

        return employeeRepository.save(existingEmployee);
    }
}