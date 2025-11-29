package com.niladri.uber_geohashing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverResponse {
    Long id;
    String name;
    String email;
    String phoneNumber;
    String licenseNumber;
    String vehicleModel;
    String vehiclePlateNumber;
    Boolean isAvailable;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
