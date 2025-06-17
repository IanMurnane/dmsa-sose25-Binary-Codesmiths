package com.mobility.vehicle_management_microservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private BigDecimal pricePerHour;

    @Column(nullable = false)
    private String billingModel;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    // Constructors
    public Vehicle() {}

    public Vehicle(Long providerId, String type, String model, BigDecimal pricePerHour,
                   String billingModel, String status, String location, LocalDateTime lastUpdated) {
        this.providerId = providerId;
        this.type = type;
        this.model = model;
        this.pricePerHour = pricePerHour;
        this.billingModel = billingModel;
        this.status = status;
        this.location = location;
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getBillingModel() {
        return billingModel;
    }

    public void setBillingModel(String billingModel) {
        this.billingModel = billingModel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
