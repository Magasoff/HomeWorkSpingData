package com.example.homeworkspingdata;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Report {
    private String departmentName;
    private int employeeCount;
    private double maxSalary;
    private double minSalary;
    private double avgSalary;
    @Column(name = "file_path")
    private String filePath;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartmentStats> departmentStats;

    @Lob
    private byte[] fileContent;

    public Report(String departmentName) {
        this.departmentName = departmentName;
        this.employeeCount = employeeCount;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.avgSalary = avgSalary;
        this.filePath = filePath;
        this.id = id;
        this.timestamp = timestamp;
        this.departmentStats = departmentStats;
        this.fileContent = fileContent;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(double avgSalary) {
        this.avgSalary = avgSalary;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<DepartmentStats> getDepartmentStats() {
        return departmentStats;
    }

    public void setDepartmentStats(List<DepartmentStats> departmentStats) {
        this.departmentStats = departmentStats;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
