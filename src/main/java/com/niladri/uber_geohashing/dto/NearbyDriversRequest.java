package com.niladri.uber_geohashing.dto;

public record NearbyDriversRequest(
    Double latitude,
    Double longitude,
    Double radius) {

}
