package org.ron.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.inventoryservice.model.Inventory;
import org.ron.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("{userId}")
    public List<Inventory> getInventory(@PathVariable Integer userId) {
        log.info("inventory id: {}", userId);

        return inventoryService.getInventoryByUserId(userId);
    }
}
