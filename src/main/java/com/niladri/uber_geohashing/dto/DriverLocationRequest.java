package com.niladri.uber_geohashing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverLocationRequest {

    String driverId;
    Double latitude;
    Double longitude;
}
