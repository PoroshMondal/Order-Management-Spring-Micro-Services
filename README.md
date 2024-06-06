# Order Management 
This is a simple practice project of Order Management developed using Spring Boot microservices 

## Modules
- api-gateway
- discovery-server
- inventory-service
- order-service
- product-service

### API (Product Service module)
There are two APIs implemented.
- Product Insert API
- Product List API

<b>Product Insert API</b>: http://localhost:8086/api/product
</br><b>Method</b>: POST

<b>Requset Body:</b> 
```
{
    "name": "IPhone 13",
    "description": "IPhone 13",
    "price":"1000"
}
```

<b>Response:</b> empty

<b> Product List API</b>: http://localhost:8086/api/product
</br><b>Method</b>: GET

<b>Request Body:</b> empty

<b>Response:</b> 
```
[
    {
        "id": 1,
        "name": "IPhone 12",
        "description": "IPhone 12",
        "price": 1000.00
    },
    {
        "id": 2,
        "name": "IPhone 13",
        "description": "IPhone 13",
        "price": 1000.00
    }
]
```

### API (Order Service module)
There are only one API is implemented

- Order API

<b>Order API:</b> http://localhost:8081/api/order
<br><b>Method:</b> POST

<b>Request Body:</b>
```
{
    "orderLineItemsDtoList":[
        {
            "skuCode": "IPhone 13",
            "price": 1200,
            "quantity": 10
        },
        {
            "skuCode": "IPhone 12",
            "price": 1200,
            "quantity": 100
        }
    ]
}
```

<b>Response:</b> Order Placed Successfully

### API (Inventory Service module)
There are only one API is implemented

- Inventory API

<b>Order API:</b> http://localhost:8081/api/order
<br><b>Method:</b> 

<b>Request Body:</b>
```
```

<b>Response:</b>