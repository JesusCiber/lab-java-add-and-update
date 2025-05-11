package com.example.demo.DTO;


public class EmployeeDepartmentDTO {
    String department;

    public EmployeeDepartmentDTO() {
    }

    public EmployeeDepartmentDTO(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployeeDepartmentDTO{" +
                "department='" + department + '\'' +
                '}';
    }
}

