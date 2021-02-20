package com.compant.erpBackend.dto;

import com.compant.erpBackend.entity.QuantityType;
import lombok.Data;

@Data
public class QuantityTypeDto {

    private Long idQuantityType;
    private String name;

    public static QuantityTypeDto of(QuantityType quantityType) {
        QuantityTypeDto dto = new QuantityTypeDto();
        dto.setName(quantityType.getName());
        dto.setIdQuantityType(quantityType.getIdQuantityType());
        return dto;
    }

    @Override
    public String toString() {
        return name;
    }
}
