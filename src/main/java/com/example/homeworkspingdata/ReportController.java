package com.example.homeworkspingdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportRepository reportRepository;

    @PostMapping
    public ResponseEntity<Long> generateReport() {
        Report report = reportService.generateReport();
        return ResponseEntity.ok(report.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getReport(@PathVariable Long id) {
        Optional<ReportList> optionalReport = reportRepository.findById(id);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("report.json")
                    .build());
            return new ResponseEntity<>(report.getFileContent(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}