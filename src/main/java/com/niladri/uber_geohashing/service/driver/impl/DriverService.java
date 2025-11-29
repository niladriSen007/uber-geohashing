package com.niladri.uber_geohashing.service.driver.impl;

import com.niladri.uber_geohashing.dto.DriverRequest;
import com.niladri.uber_geohashing.dto.DriverResponse;
import com.niladri.uber_geohashing.mapper.Mapper;
import com.niladri.uber_geohashing.model.Driver;
import com.niladri.uber_geohashing.repository.DriverRepository;
import com.niladri.uber_geohashing.service.driver.IDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverService implements IDriverService {

    private final DriverRepository driverRepository;


    @Override
    @Transactional(readOnly = true)
    public Optional<DriverResponse> findDriverById(Long id) {
        return driverRepository.findById(id).map(Mapper::toDriverResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverResponse> findAllDrivers() {
        return driverRepository.findAll().stream().map(Mapper::toDriverResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DriverResponse> findDriverByEmail(String email) {
        return driverRepository.findByEmail(email).map(Mapper::toDriverResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverResponse> findAvailableDrivers() {
        return driverRepository.findAll().stream().filter(Driver::getIsAvailable).map(Mapper::toDriverResponse).toList();
    }

    @Override
    public DriverResponse createDriver(DriverRequest request) {
        if (driverRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Driver with email already exists");
        }

        if (driverRepository.existsByLicenseNumber(request.getLicenseNumber())) {
            throw new RuntimeException("Driver with license number already exists");
        }

        return Mapper.toDriverResponse(driverRepository.save(Mapper.toDriver(request)));

    }

    @Override
    public DriverResponse updateDriver(Long id, DriverRequest request) {
        Driver driverNotFound = driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver not found"));
        if (!driverNotFound.getEmail().equals(request.getEmail()) && driverRepository.existsByEmail(request.getEmail())) {
            // when user is updating email, check if the new email already exists
            throw new RuntimeException("Driver with email already exists");
        }
        Mapper.updateDriverFromRequest(driverNotFound, request);
        return Mapper.toDriverResponse(driverRepository.save(driverNotFound));
    }

    @Override
    public void deleteDriverById(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new RuntimeException("Driver not found");
        }
        driverRepository.deleteById(id);
    }
}
