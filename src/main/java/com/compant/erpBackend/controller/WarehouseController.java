package com.compant.erpBackend.controller;

import com.compant.erpBackend.dto.WarehouseModuleDto;
import com.compant.erpBackend.entity.Employee;
import com.compant.erpBackend.entity.Warehouse;
import com.compant.erpBackend.repository.WarehouseRepository;
import com.compant.erpBackend.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseRepository warehouseRepository;

    private final WarehouseService warehouseService;

    @PostMapping("/warehouses")
    public Warehouse newWarehouse(@RequestBody Warehouse newWarehouse) {
        return warehouseRepository.save(newWarehouse);
    }

    @GetMapping("/warehouses")
    public  List<Warehouse> listWarehouses() {
        return warehouseRepository.findAll();
    }

    @DeleteMapping("/warehouses")
    public ResponseEntity deleteWarehouse(@RequestBody Long idWarehouse) {
        warehouseRepository.deleteById(idWarehouse);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/warehouse_module_data")
    public WarehouseModuleDto getWarehouseModuleData(@RequestParam Optional<String> idWarehouse) {
        if (idWarehouse.isPresent()) {
            return warehouseService.getWarehouseModuleData(Long.parseLong(idWarehouse.get()));
        } else {
            return warehouseService.getWarehouseModuleData();
        }
    }
}
