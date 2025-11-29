package com.niladri.uber_geohashing.service.driver;

import com.niladri.uber_geohashing.dto.DriverRequest;
import com.niladri.uber_geohashing.dto.DriverResponse;

public interface IDriverServiceWrite {
    DriverResponse createDriver(DriverRequest request);
    DriverResponse updateDriver(Long id, DriverRequest request);
    void deleteDriverById(Long id);
}
