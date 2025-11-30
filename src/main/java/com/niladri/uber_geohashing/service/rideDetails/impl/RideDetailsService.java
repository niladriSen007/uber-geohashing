package com.niladri.uber_geohashing.service.rideDetails.impl;

import com.niladri.uber_geohashing.dto.RideDetailsRequest;
import com.niladri.uber_geohashing.dto.RideDetailsResponse;
import com.niladri.uber_geohashing.mapper.Mapper;
import com.niladri.uber_geohashing.model.Driver;
import com.niladri.uber_geohashing.model.RideDetails;
import com.niladri.uber_geohashing.model.RideDetails.RideStatus;
import com.niladri.uber_geohashing.model.Rider;
import com.niladri.uber_geohashing.repository.DriverRepository;
import com.niladri.uber_geohashing.repository.RideDetailsRepository;
import com.niladri.uber_geohashing.repository.RiderRepository;
import com.niladri.uber_geohashing.service.rideDetails.IRideDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RideDetailsService implements IRideDetailsService {

    private final RideDetailsRepository rideDetailsRepository;
    private final RiderRepository riderRepository;
    private final DriverRepository driverRepository;


    @Override
    @Transactional(readOnly = true)
    public Optional<RideDetailsResponse> findRideDetailsById(Long id) {
        return rideDetailsRepository.findById(id).map(Mapper::toRideDetailsResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RideDetailsResponse> findAllRides() {
        return rideDetailsRepository.findAll().stream().map(Mapper::toRideDetailsResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RideDetailsResponse> findRideByPassengerId(Long passengerId) {
        Rider rider = riderRepository.findById(passengerId).orElseThrow(() -> new RuntimeException("Rider not found"));
        List<RideDetails> rideByRider = rideDetailsRepository.findByRider(rider);
        return rideByRider.stream().map(Mapper::toRideDetailsResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RideDetailsResponse> findRideByDriverId(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
        List<RideDetails> rideByDriver = rideDetailsRepository.findByDriver(driver);
        return rideByDriver.stream().map(Mapper::toRideDetailsResponse).toList();
    }

    @Override
    public RideDetailsResponse create(RideDetailsRequest request) {
        Rider rider = riderRepository.findById(request.getRiderId()).orElseThrow(() -> new RuntimeException("Rider not found"));
        RideDetails newRideDetails = RideDetails.builder()
                .rider(rider).pickupLocationLatitude(request.getPickupLocationLatitude())
                .pickupLocationLongitude(request.getPickupLocationLongitude())
                .dropoffLocationLatitude(request.getDropoffLocationLatitude())
                .dropoffLocationLongitude(request.getDropoffLocationLongitude())
                .status(RideStatus.PENDING)
                .build();


        return null;
    }

    @Override
    public RideDetailsResponse update(Long id, RideDetailsRequest request) {
        return null;
    }

    @Override
    public RideDetailsResponse updateStatus(Long id, RideStatus status) {
        RideDetails rideDetails = rideDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("RideDetails not found"));
        rideDetails.setStatus(status);

        if (status == RideStatus.IN_PROGRESS && rideDetails.getActualPickupTime() == null) {
            rideDetails.setActualPickupTime(LocalDateTime.now());
        } else if (status == RideStatus.COMPLETED) {
            rideDetails.setCompletedAt(LocalDateTime.now());
            if (rideDetails.getDriver() != null) {
                Driver driver = rideDetails.getDriver();
                driver.setIsAvailable(true);
                driverRepository.save(driver);
            }
        } else if (status == RideStatus.CANCELLED) {
            if (rideDetails.getDriver() != null) {
                Driver driver = rideDetails.getDriver();
                driver.setIsAvailable(true);
                driverRepository.save(driver);
            }
        }
        RideDetails save = rideDetailsRepository.save(rideDetails);
        return Mapper.toRideDetailsResponse(save);
    }

    @Override
    public void deleteById(Long id) {
        RideDetails rideDetails = rideDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("RideDetails not found"));
        if (rideDetails.getDriver() != null) {
            Driver driver = rideDetails.getDriver();
            driver.setIsAvailable(true);
            driverRepository.save(driver);
        }
        rideDetailsRepository.deleteById(id);
    }
}
