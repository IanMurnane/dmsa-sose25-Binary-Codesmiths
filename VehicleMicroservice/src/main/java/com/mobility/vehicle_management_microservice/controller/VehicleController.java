package com.mobility.vehicle_management_microservice.controller;

import com.mobility.vehicle_management_microservice.entity.Vehicle;
import com.mobility.vehicle_management_microservice.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return service.createVehicle(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle getVehicle(@PathVariable Long id) {
        return service.getVehicleById(id);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return service.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        service.deleteVehicle(id);
    }

    @GetMapping("/provider/{providerId}")
    public List<Vehicle> getVehiclesByProvider(@PathVariable Long providerId) {
        return service.getVehiclesByProvider(providerId);
    }

    // ✅ New endpoint: Get all vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return service.getAllVehicles();
    }
}
