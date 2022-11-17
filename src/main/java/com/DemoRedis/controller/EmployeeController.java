package com.DemoRedis.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.DemoRedis.dto.Employee;
import com.DemoRedis.dto.EmployeeIdReq;
import com.DemoRedis.service.EmployeeService;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "save")
    public ResponseEntity<Object> save(@RequestBody Employee employee) {
        try {
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Save failed");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(value = "update")
    public ResponseEntity<Object> update(@RequestBody Employee employee) {
        try {
            Employee empl = employeeService.findById(employee.getId());
            if (null != empl) {
                employeeService.update(employee);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
        	System.err.println("update failed");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<Object> delete(@RequestParam("id") Long id) {
        try {
            employeeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
        	System.err.println("delete failed");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/empl/id")
    public ResponseEntity<Object> getById(@RequestParam("id") Long id) {
        try {
            return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
        	System.err.println("find by id failed");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/empl/list")
    public ResponseEntity<Object> listAllEmployees() {
        try {
            return new ResponseEntity<>(employeeService.getAllEmployees("Employee"), HttpStatus.OK);
        } catch (Exception e) {
        	System.err.println("find all failed");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/empl/all")
    public ResponseEntity<Object> listAll() {
        try {
            Map<Long, Employee> employeeMap = employeeService.getAll();
            return new ResponseEntity<>(employeeMap, HttpStatus.OK);
        } catch (Exception e) {
        	System.err.println("find all failed");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value = "/empl/find/id")
    public ResponseEntity<Object> findAllEmpById(@RequestBody EmployeeIdReq req) {
        try {
            List<Employee> employees = employeeService.findEmployeeById(req.getId());
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
        	System.err.println("find all failed");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}