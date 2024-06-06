package com.spring.inventoryservice.service;

import com.spring.inventoryservice.dto.InventoryResponse;
import com.spring.inventoryservice.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final IInventoryRepository inventoryRepository;

    /*@Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }*/

    // Request with multiple param through url
    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13_red
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity()>0)
                            .build()
                ).toList();
    }

}
