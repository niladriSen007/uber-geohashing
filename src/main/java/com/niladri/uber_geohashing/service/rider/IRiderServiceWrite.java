package com.niladri.uber_geohashing.service.rider;

import com.niladri.uber_geohashing.dto.RiderRequest;
import com.niladri.uber_geohashing.dto.RiderResponse;

public interface IRiderServiceWrite {
  RiderResponse createRider(RiderRequest riderRequest);

  RiderResponse updateRider(Long id, RiderRequest riderRequest);

  void deleteRiderById(Long id);
}
