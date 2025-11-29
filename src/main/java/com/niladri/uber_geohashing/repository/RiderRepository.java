package com.niladri.uber_geohashing.repository;

import com.niladri.uber_geohashing.model.Rider;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
  boolean existsByEmail(String email);
    Optional<Rider> findByEmail(String email);
}
