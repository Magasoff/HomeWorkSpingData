package com.example.homeworkspingdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/report")
    public ResponseEntity<Long> generateReport(String filePath) {
        // формирование отчета
        Report report = new Report(filePath);
        report.setFilePath("/path/to/report.json");
        reportRepository.save(report);
        return ResponseEntity.ok(report.getId());
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<byte[]> getReport(@PathVariable Long id) throws IOException {
        Report report = reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report not found"));
        Path path = Paths.get(report.getFilePath());
        byte[] data = Files.readAllBytes(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("report.json").build());
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}