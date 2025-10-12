package com.shl.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.shl.store.entities.Address;
import com.shl.store.entities.Category;
import com.shl.store.entities.Product;
import com.shl.store.entities.Profile;
import com.shl.store.entities.Tag;
import com.shl.store.entities.User;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        var user = User.builder()
                .name("user1")
                .email("user1@example.com")
                .password("password1")
                .build();

        var address = Address.builder()
                .street("123 Main St")
                .city("Anytown")
                .state("CA")
                .zip("12345")
                .build();

        user.addAddress(address);

        var tag = new Tag("Tag1");

        user.addTag(tag);

        var profile = Profile.builder()
                .bio("This is user1's bio").build();

        user.setProfile(profile);
        profile.setUser(user);

        var category = Category.builder()
                .name("Electronics")
                .build();

        var product = Product.builder()
                .name("Laptop")
                .price(999.99)
                .category(category)
                .build();

        product.setCategory(category);
        category.getProducts().add(product);

        System.out.println(user.toString());
        System.out.println(product.toString());
    }

}
