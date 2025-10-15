package com.shl.store.services;

import java.math.BigDecimal;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.shl.store.entities.Address;
import com.shl.store.entities.Category;
import com.shl.store.entities.Product;
import com.shl.store.entities.User;
import com.shl.store.repositories.AddressRepository;
import com.shl.store.repositories.CategoryRepository;
import com.shl.store.repositories.ProductRepository;
import com.shl.store.repositories.ProfileRepository;
import com.shl.store.repositories.UserRepository;
import com.shl.store.repositories.specifications.ProductSpec;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional // Ensure this method runs within a transaction
    public void showEntityStates() {
        var user = User.builder()
                .name("Alice Smith")
                .email("alice.smith@example.com")
                .password("anothersecurepassword")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("User is in a managed state.");
        } else {
            System.out.println("User is in a transient/detached state.");
        }

        this.userRepository.save(user);
        if (entityManager.contains(user)) {
            System.out.println("User is in a managed state.");
        } else {
            System.out.println("User is in a transient/detached state.");
        }
    }

    @Transactional
    public void showRelatedEntities() {
        var profile = this.profileRepository.findById(1L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    @Transactional
    public void findAddress() {
        var address = this.addressRepository.findById(1L).orElseThrow();
        System.out.println(address.getState());
    }

    public void persistRelated() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("securepassword")
                .build();

        var address = Address.builder()
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .zip("62701")
                .build();

        user.addAddress(address);

        this.userRepository.save(user);
    }

    public void deleteRelated() {
        this.userRepository.deleteById(1L);
    }

    @Transactional
    public void deleteAddress() {
        var user = this.userRepository.findById(8L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        this.userRepository.save(user);
    }

    @Transactional
    public void manageProducts() {
        /** Create and save a new category and product */
        // var category = Category.builder()
        // .name("Sample Category")
        // .build();

        // var product = Product.builder()
        // .name("Sample Product")
        // .price(19.99)
        // .category(category)
        // .build();

        // this.productRepository.save(product);

        /** Retrieve category and create a new product */
        // var category = this.categoryRepository.findById(1L).orElseThrow();

        // var product = Product.builder()
        // .name("Sample Product")
        // .price(19.99)
        // .category(category)
        // .build();

        // this.productRepository.save(product);

        /** Retrieve all products and add them to the user's favorites */
        // var user = this.userRepository.findById(3L).orElseThrow();
        // var products = this.productRepository.findAll();
        // products.forEach(user::addFavoriteProduct);
        // this.userRepository.save(user);

        /** Retrieve a product and delete */
        this.productRepository.deleteById(3L);
    }

    @Transactional
    public void updateProductPrices() {
        this.productRepository.updatePriceByCategory(BigDecimal.valueOf(100), 1L);
    }

    @Transactional
    public void fetchProducts() {
        // var products = this.productRepository.findByCategory(new Category(1));
        // var products = this.productRepository.findProducts(BigDecimal.valueOf(0),
        // BigDecimal.valueOf(100));
        // products.forEach(System.out::println);
        var product = new Product();
        product.setName("sample");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        var example = Example.of(product, matcher);
        var products = this.productRepository.findAll(example);

        products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria() {
        var products = this.productRepository.findProductsByCriteria("sample", BigDecimal.valueOf(1), null);
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice,
            String categoryName) {
        Specification<Product> spec = Specification.unrestricted();

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }

        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        if (categoryName != null) {
            spec = spec.and(ProductSpec.hasCategoryName(categoryName));
        }

        this.productRepository.findAll(spec).forEach(System.out::println);
    }

    @Transactional
    public void fetchUser() {
        var user = this.userRepository.findByEmail("john.doe@example.com").orElseThrow();
        System.out.println(user);
    }

    @Transactional
    public void fetchUsers() {
        var users = this.userRepository.findUsersWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void printLoyalProfiles() {
        var profiles = this.userRepository.findLoyalUsers(49);
        profiles.forEach(p -> {
            System.out.println(p.getId());
            System.out.println(p.getEmail());
        });
    }
}
