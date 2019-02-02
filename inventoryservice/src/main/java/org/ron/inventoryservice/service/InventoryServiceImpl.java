package org.ron.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import org.ron.inventoryservice.model.Inventory;
import org.ron.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory getInventory(Integer inventoryId) {
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
        if (!inventory.isPresent()) {
            throw new NullPointerException("userId-"+inventoryId);
        }
        return inventory.get();
    }

    @Override
    public List<Inventory> getInventoryByUserId(Integer userId) {
        Optional<List<Inventory>> inventories = Optional.ofNullable(inventoryRepository.findInventoriesByUserId(userId));

        if (!inventories.isPresent()) {
            throw new NullPointerException("userId-"+userId);
        }

        return inventories.get();
    }
}
