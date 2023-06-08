package com.example.homeworkspingdata;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    void saveAll(List<Employee> employees);
}