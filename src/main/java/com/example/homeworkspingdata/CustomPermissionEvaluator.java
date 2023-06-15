package com.example.homeworkspingdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || targetDomainObject == null || !(permission instanceof String)) {
            return false;
        }

        Long employeeId = ((Employee) targetDomainObject).getId();
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) {
            return false;
        }

        String username = authentication.getName();

        if (username.equals(employee.getUsername())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}