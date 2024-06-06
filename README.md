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

<b>Requset Body:</b> empty

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
