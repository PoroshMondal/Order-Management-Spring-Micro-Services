package com.spring.inventoryservice.controller;

import com.spring.inventoryservice.dto.InventoryResponse;
import com.spring.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    /*@GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku_code") String skuCode){
        return inventoryService.isInStock(skuCode);
    }*/

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        System.out.println("get called the isInStock: " + inventoryService.isInStock(skuCode).toString());
        return inventoryService.isInStock(skuCode);
    }

}
