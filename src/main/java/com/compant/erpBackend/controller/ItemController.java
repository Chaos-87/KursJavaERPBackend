package com.compant.erpBackend.controller;

import com.compant.erpBackend.entity.Employee;
import com.compant.erpBackend.entity.Item;
import com.compant.erpBackend.repository.EmployeeRepository;
import com.compant.erpBackend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @PostMapping("/items")
    Item newItem(@RequestBody Item newItem) {
        return itemRepository.save(newItem);
    }

    @GetMapping("/items")
    List<Item> listItems() {
        return itemRepository.findAll();
    }

    @DeleteMapping("/items")
    ResponseEntity deleteItem(@RequestBody Long idItem) {
        itemRepository.deleteById(idItem);

        return ResponseEntity.ok().build();
    }
}
