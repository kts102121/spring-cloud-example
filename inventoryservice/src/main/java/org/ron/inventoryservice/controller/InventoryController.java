package org.ron.inventoryservice.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.inventoryservice.model.Inventory;
import org.ron.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/inventory")
public class InventoryController {
    private final Gson gson;
    private final InventoryService inventoryService;

    @GetMapping("{userId}")
    public List<Inventory> getInventory(@PathVariable Integer userId, HttpServletRequest request) {
        log.info("Authorization: {}", request.getHeader("Authorization"));
        log.info("header passed to inventory service: {}", gson.toJson(getHeaderInfo(request), new TypeToken<Map<String, String>>() {}.getType()));

        return inventoryService.getInventoryByUserId(userId);
    }

    private Map<String, String> getHeaderInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}
