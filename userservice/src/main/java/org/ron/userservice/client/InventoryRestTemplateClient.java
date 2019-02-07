package org.ron.userservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.userservice.model.Inventory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryRestTemplateClient {
    private final RestTemplate restTemplate;

    public List<Inventory> getInventory(Integer userId) {
        ResponseEntity<List<Inventory>> restExchange =
                restTemplate.exchange(
                        "http://gatewayservice/inventoryservice/v1/inventory/{userId}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Inventory>>(){},
                        userId
                );

        return restExchange.getBody();
    }
}
