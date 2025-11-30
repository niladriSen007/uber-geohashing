package com.niladri.uber_geohashing.repository;

import com.niladri.uber_geohashing.model.Driver;
import com.niladri.uber_geohashing.model.RideDetails;
import com.niladri.uber_geohashing.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RideDetailsRepository extends JpaRepository<RideDetails, Long> {


    List<RideDetails> findByRider(Rider rider);

    List<RideDetails> findByDriver(Driver driver);

    Optional<RideDetails> findByIdAndRider(Long id, Rider rider);

    Optional<RideDetails> findByIdAndDriver(Long id, Driver driver);
}
