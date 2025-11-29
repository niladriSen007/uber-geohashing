package com.niladri.uber_geohashing.mapper;

import com.niladri.uber_geohashing.dto.*;
import com.niladri.uber_geohashing.model.Driver;
import com.niladri.uber_geohashing.model.RideDetails;
import com.niladri.uber_geohashing.model.Rider;

public class Mapper {
    public static RiderResponse toRiderResponse(Rider rider) {
        return RiderResponse.builder()
                .id(rider.getId())
                .name(rider.getName())
                .email(rider.getEmail())
                .phoneNumber(rider.getPhoneNumber())
                .createdAt(rider.getCreatedAt())
                .updatedAt(rider.getUpdatedAt())
                .build();
    }

    public static Rider toRider(RiderRequest riderRequest) {
        return Rider.builder()
                .name(riderRequest.getName())
                .email(riderRequest.getEmail())
                .phoneNumber(riderRequest.getPhoneNumber())
                .build();
    }

    public static Driver toDriver(DriverRequest request) {
        return Driver.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .licenseNumber(request.getLicenseNumber())
                .vehicleModel(request.getVehicleModel())
                .vehiclePlateNumber(request.getVehiclePlateNumber())
                .isAvailable(request.getIsAvailable())
                .build();
    }

    public static DriverResponse toDriverResponse(Driver driver) {
        return DriverResponse.builder()
                .id(driver.getId())
                .name(driver.getName())
                .email(driver.getEmail())
                .phoneNumber(driver.getPhoneNumber())
                .licenseNumber(driver.getLicenseNumber())
                .vehicleModel(driver.getVehicleModel())
                .vehiclePlateNumber(driver.getVehiclePlateNumber())
                .isAvailable(driver.getIsAvailable())
                .createdAt(driver.getCreatedAt())
                .updatedAt(driver.getUpdatedAt())
                .build();
    }

    public static RideDetailsResponse toRideDetailsResponse(RideDetails booking) {
        return RideDetailsResponse.builder()
                .id(booking.getId())
                .passengerId(booking.getRider() != null ? booking.getRider().getId() : null)
                .passengerName(booking.getRider() != null ? booking.getRider().getName() : null)
                .driverId(booking.getDriver() != null ? booking.getDriver().getId() : null)
                .driverName(booking.getDriver() != null ? booking.getDriver().getName() : null)
                .pickupLocation(booking.getPickupLocationLatitude() )
                .dropoffLocation(booking.getDropoffLocationLatitude() )
                .status(booking.getStatus())
                .fare(booking.getFare())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .scheduledPickupTime(booking.getScheduledPickupTime())
                .actualPickupTime(booking.getActualPickupTime())
                .completedAt(booking.getCompletedAt())
                .build();
    }

    public static void updateRiderFromRequest(Rider rider, RiderRequest riderRequest) {
        rider.setName(riderRequest.getName());
        rider.setEmail(riderRequest.getEmail());
        rider.setPhoneNumber(riderRequest.getPhoneNumber());
    }

    public static void updateDriverFromRequest(Driver driver, DriverRequest request) {
        driver.setName(request.getName());
        driver.setEmail(request.getEmail());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setLicenseNumber(request.getLicenseNumber());
        driver.setVehicleModel(request.getVehicleModel());
        driver.setVehiclePlateNumber(request.getVehiclePlateNumber());
        driver.setIsAvailable(request.getIsAvailable());
    }
}
