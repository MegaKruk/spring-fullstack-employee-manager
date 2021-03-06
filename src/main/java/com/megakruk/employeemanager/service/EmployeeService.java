package com.megakruk.employeemanager.service;

import com.megakruk.employeemanager.exception.UserNotFoundException;
import com.megakruk.employeemanager.model.Employee;
import com.megakruk.employeemanager.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return  employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id).orElseThrow(() ->
                new UserNotFoundException("User with id " + id + " was not found"));
    }

    public void deleteEmployee(Long id) {
        try {
            employeeRepo.deleteEmployeeById(id);
        } catch (Exception e) {
            System.err.println("Delete failed.\n" + e.toString());
        }
    }
}
