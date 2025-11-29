package com.niladri.uber_geohashing.service.rideDetails;

import com.niladri.uber_geohashing.dto.RideDetailsResponse;

import java.util.List;
import java.util.Optional;

public interface IRideDetailsServiceRead {
    Optional<RideDetailsResponse> findRideDetailsById(Long id);

    List<RideDetailsResponse> findAllRides();

    List<RideDetailsResponse> findRideByPassengerId(Long passengerId);

    List<RideDetailsResponse> findRideByDriverId(Long driverId);
}
