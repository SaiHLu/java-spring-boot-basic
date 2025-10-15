package com.shl.store.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shl.store.dtos.ProductSummary;
import com.shl.store.entities.Category;
import com.shl.store.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // String
    List<Product> findByName(String name);

    List<Product> findByNameLike(String name);

    List<Product> findByNameNotLike(String name);

    List<Product> findByNameStartingWith(String name);

    List<Product> findByNameEndingWith(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByNameLikeIgnoreCase(String name);

    // Number
    List<Product> findByPrice(BigDecimal price);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceGreaterThanEqual(BigDecimal price);

    List<Product> findByPriceLessThanEqual(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // Null
    List<Product> findByDescriptionIsNull();

    List<Product> findByDescriptionIsNotNull();

    // Multiple conditions (And, Or)
    List<Product> findByNameIsNullAndDescriptionIsNull();

    // Sort (OrderBy)
    List<Product> findByNameOrderByPrice(String name);

    // Limit (Top, First)
    List<Product> findTop5ByNameOrderByPrice(String name);

    List<Product> findFirst5ByNameLikeIgnoreCaseOrderByPrice(String name);

    // SQL or JPQL
    // SQL
    // @Query(value = "select * from products p where p.price between :min and :max
    // order by p.price", nativeQuery = true)
    // JPQL
    // @Query("select p from Product p where p.price between :min and :max order by
    // p.price")
    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);

    @Query(value = "select count(*) from products p where p.price between :min and :max", nativeQuery = true)
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(@Param("newPrice") BigDecimal newPrice, @Param("categoryId") Long categoryId);

    List<ProductSummary> findByCategory(Category category);
}
