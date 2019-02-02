package org.ron.inventoryservice.service;

import org.ron.inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory getInventory(Integer inventoryId);

    List<Inventory> getInventoryByUserId(Integer userId);
}
