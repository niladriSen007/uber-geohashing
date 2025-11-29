package com.niladri.uber_geohashing.service.driver;

import com.niladri.uber_geohashing.dto.DriverResponse;

import java.util.List;
import java.util.Optional;

public interface IDriverServiceRead {

    Optional<DriverResponse> findDriverById(Long id);

    List<DriverResponse> findAllDrivers();

    Optional<DriverResponse> findDriverByEmail(String email);

    List<DriverResponse> findAvailableDrivers();
}
