package com.niladri.uber_geohashing.dto;

public record DriverLocationRequest(
    String driverId,
    Double latitude,
    Double longitude) {

}
