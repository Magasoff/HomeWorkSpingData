package com.example.homeworkspingdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByPositionName(String positionName);

    public List<Employee> getAllEmployees();
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    Employee save(Employee employee);
    void update(Employee employee);
    void deleteById(Long id);
    List<Employee> findBySalaryGreaterThan(Integer salary);

    List<Employee> findByDepartment(String department);

    List<String> findDistinctDepartments();
}