package com.shl.store.repositories;

import com.shl.store.entities.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
