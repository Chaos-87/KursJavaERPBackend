package com.compant.erpBackend.repository;

import com.compant.erpBackend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
