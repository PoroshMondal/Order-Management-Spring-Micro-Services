package com.spring.orderservice.service;

import com.spring.orderservice.dto.InventoryResponse;
import com.spring.orderservice.dto.OrderLineItemsDto;
import com.spring.orderservice.dto.OrderRequest;
import com.spring.orderservice.model.Order;
import com.spring.orderservice.model.OrderLineItems;
import com.spring.orderservice.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final IOrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
                .toList();
        order.setOrderLineItems(orderLineItems);

        // map all the skuCodes
        List<String> skuCodes = order.getOrderLineItems()
                .stream()
                .map(orderLineItem -> orderLineItem.getSkuCode())
                .toList();

        // call the Inventory service, and place order if product is in stock
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                //.uri("http://localhost:8082/api/inventory",
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        System.out.println("is stock array in service class: " + Arrays.stream(inventoryResponses).toList());

        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.isInStock());

        System.out.println("is stock value in service class: " + allProductsInStock);

        if (allProductsInStock){
            orderRepository.save(order);
        }else {
            //System.out.println("Product is not in stock, please try later");
            throw new IllegalArgumentException("Product is not in stock, please try later");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

}
