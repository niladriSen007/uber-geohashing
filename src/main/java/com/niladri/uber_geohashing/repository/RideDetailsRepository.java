package com.niladri.uber_geohashing.repository;

import com.niladri.uber_geohashing.model.RideDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideDetailsRepository extends JpaRepository<RideDetails, Long> {
}
