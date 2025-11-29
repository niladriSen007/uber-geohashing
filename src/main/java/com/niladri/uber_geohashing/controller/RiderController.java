package com.niladri.uber_geohashing.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niladri.uber_geohashing.dto.RiderRequest;
import com.niladri.uber_geohashing.model.Rider;
import com.niladri.uber_geohashing.service.rider.impl.RiderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/riders")
public class RiderController {

  private RiderService riderService;

  @PostMapping
  public ResponseEntity<Rider> createRider(@Valid @RequestBody RiderRequest rider) {
    return ResponseEntity.ok(riderService.saveRider(rider));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Rider> getRider(@PathVariable Long id) {
    return riderService.getRider(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Rider>> getAllRiders() {
    return ResponseEntity.ok(riderService.getAllRiders());
  }
}
