package com.example.homeworkspingdata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${app.env}")
    private String appEnv;

    @GetMapping("/appInfo")
    public String getAppInfo() {
        return "Application environment: " + appEnv;
    }
}