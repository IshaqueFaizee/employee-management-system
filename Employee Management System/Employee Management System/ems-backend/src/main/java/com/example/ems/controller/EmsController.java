
package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.model.PunchLog;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.repository.PunchLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmsController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PunchLogRepository punchLogRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(employeeDetails.getName());
            employee.setAge(employeeDetails.getAge());
            employee.setGender(employeeDetails.getGender());
            employee.setAddress(employeeDetails.getAddress());
            employee.setUserId(employeeDetails.getUserId());
            employee.setPassword(employeeDetails.getPassword());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new NoSuchElementException("Employee not found with ID: " + id));
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new NoSuchElementException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
    }

    @PostMapping("/punchin/{employeeId}")
    public PunchLog punchIn(@PathVariable Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new NoSuchElementException("Employee not found with ID: " + employeeId);
        }
        PunchLog log = new PunchLog();
        log.setEmployeeId(employeeId);
        log.setPunchIn(LocalDateTime.now());
        return punchLogRepository.save(log);
    }

    @PostMapping("/punchout/{logId}")
    public PunchLog punchOut(@PathVariable Long logId) {
        return punchLogRepository.findById(logId).map(log -> {
            log.setPunchOut(LocalDateTime.now());
            return punchLogRepository.save(log);
        }).orElseThrow(() -> new NoSuchElementException("PunchLog not found with ID: " + logId));
    }

    @GetMapping("/punchlogs")
    public List<PunchLog> getAllPunchLogs() {
        return punchLogRepository.findAll();
    }
}
