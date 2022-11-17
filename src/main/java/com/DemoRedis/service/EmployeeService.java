package com.DemoRedis.service;



import java.util.List;
import java.util.Map;

import com.DemoRedis.dto.Employee;

public interface EmployeeService {

    void save(Employee employee);
    void update(Employee employee);
    void delete(Long id);
    Employee findById(Long id);
    List<Employee> getAllEmployees(String key);
    List<Employee> findEmployeeById(List<Long> id);
    Map<Long, Employee> getAll();
}