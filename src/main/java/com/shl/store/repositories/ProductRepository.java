package com.shl.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shl.store.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
