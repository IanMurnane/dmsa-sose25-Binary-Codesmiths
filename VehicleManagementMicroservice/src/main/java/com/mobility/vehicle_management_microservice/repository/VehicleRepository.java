package com.mobility.vehicle_management_microservice.repository;

import com.mobility.vehicle_management_microservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByProviderId(Long providerId);
}
