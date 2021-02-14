package com.compant.erpBackend.controller;

import com.compant.erpBackend.dto.EmployeeDto;
import com.compant.erpBackend.entity.Employee;
import com.compant.erpBackend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public EmployeeDto newEmployee(@RequestBody EmployeeDto newEmployee) {
        return EmployeeDto.of(employeeRepository.save(Employee.of(newEmployee)));
    }

    @GetMapping("/employees")
    public List<EmployeeDto> listEmployees() {


        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDto::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/employees/{idEmployee}")
    public EmployeeDto getEmployee(@PathVariable Long idEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(idEmployee);

        return EmployeeDto.of(optionalEmployee.get());
    }

    @DeleteMapping("/employees")
    public ResponseEntity deleteEmployee(@RequestBody Long idEmployee) {
        employeeRepository.deleteById(idEmployee);

        return ResponseEntity.ok().build();
    }
}
