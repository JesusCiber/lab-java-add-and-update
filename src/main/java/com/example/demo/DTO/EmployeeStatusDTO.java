package com.example.demo.DTO;


import com.example.demo.EmployeeStatus;

public class EmployeeStatusDTO {
    EmployeeStatus status;

    public EmployeeStatusDTO() {
    }

    public EmployeeStatusDTO(EmployeeStatus status) {
        this.status = status;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeeStatusDto{" +
                "status=" + status +
                '}';
    }
}
