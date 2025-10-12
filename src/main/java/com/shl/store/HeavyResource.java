package com.shl.store;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy // To avoid eager initialization
public class HeavyResource {
    public HeavyResource() {
        System.out.println("HeavyResource initialized");
    }
}
