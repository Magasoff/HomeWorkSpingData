package com.example.homeworkspingdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
    public class ReportService {
        @Autowired
        private EmployeeRepository employeeRepository;

        @Autowired
        private ReportRepository reportRepository;

        @PostMapping("/batch")
        public ResponseEntity<Void> saveReports(@RequestBody ReportList reportList) {
            reportRepository.save(reportList);
            return ResponseEntity.ok().build();
        }

        public Report generateReport() {
            List<Object[]> results = employeeRepository.getStatisticsByDepartment();
            List<Report> reports = new ArrayList<>();
            for (Object[] result : results) {
                Report report = new Report(filePath);
                report.setDepartmentName((String) result[0]);
                report.setEmployeeCount(((Number) result[1]).intValue());
                report.setMaxSalary((Double) result[2]);
                report.setMinSalary((Double) result[3]);
                report.setAvgSalary((Double) result[4]);
                reports.add(report);
            }
            byte[] fileContent = generateReportFile(reports);
            Report report = new Report(filePath);
            report.setDepartmentName("All departments");
            report.setEmployeeCount(reports.stream().mapToInt(Report::getEmployeeCount).sum());
            report.setMaxSalary(reports.stream().mapToDouble(Report::getMaxSalary).max().orElse(0));
            report.setMinSalary(reports.stream().mapToDouble(Report::getMinSalary).min().orElse(0));
            report.setAvgSalary(reports.stream().mapToDouble(Report::getAvgSalary).average().orElse(0));
            report.setFileContent(fileContent);
            return reportRepository.save(report);
        }

        private byte[] generateReportFile(List<Report> reports) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModule(new JavaTimeModule());
            try {
                return objectMapper.writeValueAsBytes(reports);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error generating report file", e);
            }
        }
    }


