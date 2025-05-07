package org.example.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Kích hoạt auto-configuration, component scan, v.v.
public class RecipeSharingWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeSharingWebsiteApplication.class, args);
        System.out.println("=== RecipeSharingWebsiteApplication started successfully ===");
        System.out.println("Truy cập http://localhost:8080/templates/auth/register hoặc /auth/login để test giao diện.");
    }

}
