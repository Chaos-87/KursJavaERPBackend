package com.compant.erpBackend.controller;

import com.compant.erpBackend.dto.ItemDto;
import com.compant.erpBackend.dto.ItemSaveDto;
import com.compant.erpBackend.entity.Employee;
import com.compant.erpBackend.entity.Item;
import com.compant.erpBackend.repository.EmployeeRepository;
import com.compant.erpBackend.repository.ItemRepository;
import com.compant.erpBackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    private final ItemService itemService;

    @PostMapping("/items")
    public ItemDto newItem(@RequestBody ItemSaveDto dto) {
        return ItemDto.of(itemService.saveItem(dto));
    }

    @GetMapping("/items")
    public List<ItemDto> listItems() {
        return itemRepository.findAll().stream().map(ItemDto::of).collect(Collectors.toList());
    }

    @DeleteMapping("/items")
    public ResponseEntity deleteItem(@RequestBody Long idItem) {
        itemRepository.deleteById(idItem);

        return ResponseEntity.ok().build();
    }
}
