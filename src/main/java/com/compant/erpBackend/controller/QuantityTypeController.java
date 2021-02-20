package com.compant.erpBackend.controller;

import com.compant.erpBackend.dto.QuantityTypeDto;
import com.compant.erpBackend.entity.QuantityType;
import com.compant.erpBackend.repository.QuantityTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class QuantityTypeController {


    private final QuantityTypeRepository quantityTypeRepository;

    @PostMapping("/quantity_types")
    public QuantityType newQuantityType(@RequestBody QuantityType quantityType) {
        return quantityTypeRepository.save(quantityType);
    }

    @GetMapping("/quantity_types")
    public List<QuantityTypeDto> listQuantityTypes() {
        return quantityTypeRepository.findAll().stream().map(QuantityTypeDto::of).collect(Collectors.toList());
    }

    @DeleteMapping("/quantity_types")
    public ResponseEntity deleteQuantityType(@RequestBody Long idQuantityType) {
        quantityTypeRepository.deleteById(idQuantityType);

        return ResponseEntity.ok().build();
    }
}
