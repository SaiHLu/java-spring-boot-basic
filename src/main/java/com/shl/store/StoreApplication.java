package com.shl.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.shl.store.services.UserService;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        var userService = context.getBean(UserService.class);
        // userService.showEntityStates();
        // userService.showRelatedEntities();
        // userService.findAddress();
        // userService.persistRelated();
        // userService.deleteRelated();
        // userService.deleteAddress();
        // userService.manageProducts();
        // userService.updateProductPrices();
        // userService.fetchProducts();
        // userService.fetchUser();
        userService.fetchUsers();
    }
}
