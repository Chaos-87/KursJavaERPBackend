package com.compant.erpBackend.service;

import com.compant.erpBackend.dto.ItemSaveDto;
import com.compant.erpBackend.entity.Item;
import com.compant.erpBackend.entity.QuantityType;
import com.compant.erpBackend.entity.Warehouse;
import com.compant.erpBackend.repository.ItemRepository;
import com.compant.erpBackend.repository.QuantityTypeRepository;
import com.compant.erpBackend.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final QuantityTypeRepository quantityTypeRepository;

    public Item saveItem(ItemSaveDto dto) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(dto.getIdWarehouse());

        Optional<QuantityType> quantityTypeOptional = quantityTypeRepository.findById(dto.getIdQuantityType());

        if (!warehouseOptional.isPresent() || !quantityTypeOptional.isPresent()) {
            throw new RuntimeException("Incorrect identifiers: id:wearhouse: " + dto.getIdWarehouse()
            + ", idQuantityType: " + dto.getIdQuantityType());
        }

        Warehouse warehouse = warehouseOptional.get();
        QuantityType quantityType = quantityTypeOptional.get();

        Item item = Item.of(dto);
        item.setQuantityType(quantityType);
        item.setWarehouse(warehouse);

        return itemRepository.save(item);
    }
}
