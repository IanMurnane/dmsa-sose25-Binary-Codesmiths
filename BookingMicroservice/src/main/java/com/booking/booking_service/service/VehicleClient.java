package com.booking.booking_service.service;

import com.booking.booking_service.dto.VehicleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "VEHICLE-SERVICE")
public interface VehicleClient {

    @GetMapping("/vehicles")
    List<VehicleDTO> getAllVehicles();

    @GetMapping("/vehicles/{id}")
    VehicleDTO getVehicleById(@PathVariable("id") Long id);
}
