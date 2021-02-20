package com.compant.erpBackend.dto;

import com.compant.erpBackend.entity.Warehouse;
import lombok.Data;

@Data
public class WarehouseDto {

    private Long idWarehouse;
    private String name;


    public static WarehouseDto of(Warehouse warehouse) {
        WarehouseDto dto = new WarehouseDto();

        dto.setIdWarehouse(warehouse.getIdWarehouse());
        dto.setName(warehouse.getName());

        return dto;
    }
}
