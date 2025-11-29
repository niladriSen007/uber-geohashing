package com.niladri.uber_geohashing.controller;

import com.niladri.uber_geohashing.model.RideDetails;
import com.niladri.uber_geohashing.service.rideDetails.impl.RideDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ridedetails")
public class RideDetailsController {

  @Autowired
  private RideDetailsService rideDetailsService;

  @PostMapping
  public ResponseEntity<RideDetails> createRideDetails(@RequestBody RideDetails rideDetails) {
    return ResponseEntity.ok(rideDetailsService.saveRideDetails(rideDetails));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RideDetails> getRideDetails(@PathVariable Long id) {
    return rideDetailsService.getRideDetails(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<RideDetails>> getAllRideDetails() {
    return ResponseEntity.ok(rideDetailsService.getAllRideDetails());
  }
}
