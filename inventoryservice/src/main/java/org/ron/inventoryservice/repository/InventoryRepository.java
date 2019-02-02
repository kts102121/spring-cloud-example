package org.ron.inventoryservice.repository;

import org.ron.inventoryservice.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {
    List<Inventory> findInventoriesByUserId(Integer userId);
}
