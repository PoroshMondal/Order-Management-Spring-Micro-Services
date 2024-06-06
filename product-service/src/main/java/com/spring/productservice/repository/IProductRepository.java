package com.spring.productservice.repository;

import com.spring.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, String> {

}
