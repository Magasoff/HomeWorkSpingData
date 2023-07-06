package com.example.homeworkspingdata;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = (Employee) employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        Employee savedEmployee = (Employee) employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    public Employee createEmployee(Employee employee) {
        logger.info("Was invoked method for create employee {}", employee);
        Employee savedEmployee = employeeRepository.save(employee);
        logger.debug("Employee {} was successfully saved", savedEmployee);
        return savedEmployee;
    }

    public Employee getEmployeeById(long id) {
        logger.info("Was invoked method for get employee by id {}", id);
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                logger.debug("Employee {} was successfully found by id {}", employee, id);
                return employee;
            } else {
                logger.error("There is no employee with id = {}", id);
                throw new EmployeeNotFoundException("There is no employee with id = " + id);
            }
        } catch (Exception e) {
            logger.error("Error while getting employee by id {}", id, e);
            throw e;
        }
    }
}
