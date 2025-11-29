package com.niladri.uber_geohashing.service.rideDetails;

import com.niladri.uber_geohashing.dto.RideDetailsRequest;
import com.niladri.uber_geohashing.dto.RideDetailsResponse;
import com.niladri.uber_geohashing.model.RideDetails.RideStatus;

public interface IRideDetailsServiceWrite {
    RideDetailsResponse create(RideDetailsRequest request);

    RideDetailsResponse update(Long id, RideDetailsRequest request);

    RideDetailsResponse updateStatus(Long id, RideStatus status);

    void deleteById(Long id);
}
