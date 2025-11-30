package com.niladri.uber_geohashing.controller;

import com.niladri.uber_geohashing.dto.RideDetailsRequest;
import com.niladri.uber_geohashing.dto.RideDetailsResponse;
import com.niladri.uber_geohashing.model.RideDetails;
import com.niladri.uber_geohashing.service.rideDetails.impl.RideDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ride")
public class RideDetailsController {

    @Autowired
    private RideDetailsService rideDetailsService;

    @GetMapping
    public ResponseEntity<List<RideDetailsResponse>> getAllBookings() {
        List<RideDetailsResponse> bookings = rideDetailsService.findAllRides();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RideDetailsResponse> getBookingById(@PathVariable Long id) {
        return rideDetailsService.findRideDetailsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/passenger/{passengerId}")
    public ResponseEntity<List<RideDetailsResponse>> getBookingsByPassenger(@PathVariable Long passengerId) {
        try {
            List<RideDetailsResponse> bookings = rideDetailsService.findRideByPassengerId(passengerId);
            return ResponseEntity.ok(bookings);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<RideDetailsResponse>> getBookingsByDriver(@PathVariable Long driverId) {
        try {
            List<RideDetailsResponse> bookings = rideDetailsService.findRideByDriverId(driverId);
            return ResponseEntity.ok(bookings);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<RideDetailsResponse> createBooking(@Valid @RequestBody RideDetailsRequest request) {
        try {
            RideDetailsResponse booking = rideDetailsService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RideDetailsResponse> updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody RideDetailsRequest request) {
        try {
            RideDetailsResponse booking = rideDetailsService.update(id, request);
            return ResponseEntity.ok(booking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RideDetailsResponse> updateBookingStatus(
            @PathVariable Long id,
            @RequestParam RideDetails.RideStatus status) {
        try {
            RideDetailsResponse booking = rideDetailsService.updateStatus(id, status);
            return ResponseEntity.ok(booking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            rideDetailsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
