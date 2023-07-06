package com.example.homeworkspingdata;

public class DepartmentStats {

    String name;
    private int totalSalary;

    public DepartmentStats(String name, int totalSalary) {
        this.name = name;
        this.totalSalary = totalSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }
}