package com.niladri.uber_geohashing.service.rider;

import java.util.List;
import java.util.Optional;

import com.niladri.uber_geohashing.dto.RiderResponse;

public interface IRiderServiceRead {
  Optional<RiderResponse> findRiderById(Long id);

  List<RiderResponse> findAllRiders();

  Optional<RiderResponse> findByEmail(String email);
}
