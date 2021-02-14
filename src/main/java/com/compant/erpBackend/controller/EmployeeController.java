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
    public EmployeeDto saveOrUpdateEmployee(@RequestBody EmployeeDto employeeDto) {

        // nowy pracownik
        if (employeeDto.getIdEmployee() == null) {
            return EmployeeDto.of(employeeRepository.save(Employee.of(employeeDto)));
        }
        // aktualizacja obecnego
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDto.getIdEmployee());
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.updateEmployee(employeeDto);
            return EmployeeDto.of(employeeRepository.save(employee));
        } else {
            throw new RuntimeException("Can't find user with give id: " + employeeDto.getIdEmployee());
        }

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
