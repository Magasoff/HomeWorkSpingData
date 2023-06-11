package com.example.homeworkspingdata;

public class EmployeeDto {
    private Integer id;
    private String name;
    private Integer salary;
    private Long positionId;

    public EmployeeDto(Integer id, String name, Integer salary, Long positionId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.positionId = positionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}