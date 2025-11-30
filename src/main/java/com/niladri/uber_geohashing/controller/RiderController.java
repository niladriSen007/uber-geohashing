package com.niladri.uber_geohashing.controller;

import com.niladri.uber_geohashing.dto.RiderRequest;
import com.niladri.uber_geohashing.dto.RiderResponse;
import com.niladri.uber_geohashing.service.rider.impl.RiderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/riders")
public class RiderController {

    private RiderService riderService;

    @GetMapping
    public ResponseEntity<List<RiderResponse>> getAllPassengers() {
        List<RiderResponse> passengers = riderService.findAllRiders();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiderResponse> getPassengerById(@PathVariable Long id) {
        return riderService.findRiderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<RiderResponse> getPassengerByEmail(@PathVariable String email) {
        return riderService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RiderResponse> createPassenger(@Valid @RequestBody RiderRequest request) {
        try {
            RiderResponse passenger = riderService.createRider(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(passenger);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiderResponse> updatePassenger(
            @PathVariable Long id,
            @Valid @RequestBody RiderRequest request) {
        try {
            RiderResponse passenger = riderService.updateRider(id, request);
            return ResponseEntity.ok(passenger);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        try {
            riderService.deleteRiderById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
