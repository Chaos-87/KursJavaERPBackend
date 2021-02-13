package com.compant.erpBackend.controller;

import com.compant.erpBackend.dto.OperationCredentialsDto;
import com.compant.erpBackend.dto.OperatorAuthenticationResultDto;
import com.compant.erpBackend.entity.Employee;
import com.compant.erpBackend.entity.Operator;
import com.compant.erpBackend.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorRepository operatorRepository;

    @PostMapping("/operators")
    public Operator newOperator(@RequestBody Operator newOperator) {
        return operatorRepository.save(newOperator);
    }

    @GetMapping("/operators")
    public List<Operator> listOperators() {
        return operatorRepository.findAll();
    }

    @DeleteMapping("/operators")
    public ResponseEntity deleteOperator(@RequestBody Long idOperator) {
        operatorRepository.deleteById(idOperator);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify_operator_credentials")
    public OperatorAuthenticationResultDto verifyOperatorCredentials(@RequestBody OperationCredentialsDto operationCredentialsDto) {

        Optional<Operator> operatorOptional = operatorRepository.findByLogin(operationCredentialsDto.getLogin());

        if (!operatorOptional.isPresent()) {
            return OperatorAuthenticationResultDto.createUnauthenticated();
        }

        Operator operator = operatorOptional.get();

        if (!operator.getPassword().equals(operationCredentialsDto.getPassword())) {
            return OperatorAuthenticationResultDto.createUnauthenticated();
        }

        return OperatorAuthenticationResultDto.of(operator);
    }
}
