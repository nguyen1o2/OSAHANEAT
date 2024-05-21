package com.nguyen1o2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DaOsahaneatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaOsahaneatApplication.class, args);
    }

}
