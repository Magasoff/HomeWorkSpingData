package com.example.homeworkspingdata;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class ReportList extends Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Report> reports;

    @Column(name = "file_path")
    private String filePath;

    public ReportList() {
        super(null);
    }

    public ReportList(List<Report> reports) {
        super(null);
        this.reports = reports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public byte[] getFileContent() {
        return null;
    }
}

