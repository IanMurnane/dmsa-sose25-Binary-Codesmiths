package com.mobility.vehicle_management_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class VehicleManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VehicleManagementServiceApplication.class, args);
    }
}
