package com.mobility.vehicle_management_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebMvc
public class VehicleManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VehicleManagementServiceApplication.class, args);
    }
}
