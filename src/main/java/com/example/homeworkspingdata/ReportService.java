package com.example.homeworkspingdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportRepository reportRepository;

    public Long generateReport() {
        List<Report> reports = new ArrayList<>();
        List<String> departments = employeeRepository.findDistinctDepartments();
        for (String department : departments) {
            List<Employee> employees = employeeRepository.findByDepartment(department);
            int employeeCount = employees.size();
            double maxSalary = employees.stream().mapToDouble(Employee::getSalary).max().orElse(0);
            double minSalary = employees.stream().mapToDouble(Employee::getSalary).min().orElse(0);
            double avgSalary = employees.stream().mapToDouble(Employee::getSalary).average().orElse(0);
            Report report = new Report(department, employeeCount, maxSalary, minSalary, avgSalary);
            reports.add(report);
        }
        ReportList reportList = new ReportList(reports);
        ReportList savedReportList = reportRepository.save(reportList);
        return savedReportList.getId();
    }

    public Report getReportById(Long id) {
        Optional<ReportList> reportListOptional = reportRepository.findById(id);
        if (reportListOptional.isPresent()) {
            ReportList reportList = reportListOptional.get();
            return (Report) reportList.getReports();
        }
        return null;
    }
}