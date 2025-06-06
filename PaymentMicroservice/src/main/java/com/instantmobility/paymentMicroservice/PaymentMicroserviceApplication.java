package com.instantmobility.paymentMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMicroserviceApplication.class, args);
    }
}