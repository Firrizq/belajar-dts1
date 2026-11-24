package com.rmn.controller;

import com.rmn.model.Employee;
import com.rmn.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{code}")
    public Employee getByCode(@PathVariable String code) {
        return service.getByCode(code)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        return service.create(emp);
    }

    @PutMapping("/{code}")
    public Employee update(@PathVariable String code, @RequestBody Employee emp) {
        return service.update(code, emp);
    }

    @DeleteMapping("/{code}")
    public String delete(@PathVariable String code) {
        service.delete(code);
        return "Deleted: " + code;
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String zipcode) {
        return service.searchEmployees(name, zipcode);
    }
}