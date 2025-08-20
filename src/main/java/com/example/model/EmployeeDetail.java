package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetail {
    private String id;
    private int age;
    private int experienceYears;
    private String departmentName;
    private String project;
}
