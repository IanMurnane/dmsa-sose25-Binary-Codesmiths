package com.mobility.vehicle_management_microservice.service;

import com.mobility.vehicle_management_microservice.entity.Vehicle;
import com.mobility.vehicle_management_microservice.exception.VehicleNotFoundException;
import com.mobility.vehicle_management_microservice.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    public Vehicle getVehicleById(Long id) {
        return repository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }

    public List<Vehicle> getVehiclesByProvider(Long providerId) {
        return repository.findByProviderId(providerId);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setType(updatedVehicle.getType());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setPricePerHour(updatedVehicle.getPricePerHour());
        vehicle.setBillingModel(updatedVehicle.getBillingModel());
        vehicle.setStatus(updatedVehicle.getStatus());
        vehicle.setLocation(updatedVehicle.getLocation());
        return repository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        repository.deleteById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return repository.findAll();
    }
}
