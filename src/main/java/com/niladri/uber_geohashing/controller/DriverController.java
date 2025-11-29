package com.niladri.uber_geohashing.controller;

import com.niladri.uber_geohashing.model.Driver;
import com.niladri.uber_geohashing.service.driver.impl.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

  @Autowired
  private DriverService driverService;

  @PostMapping
  public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
    return ResponseEntity.ok(driverService.saveDriver(driver));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Driver> getDriver(@PathVariable Long id) {
    return driverService.getDriver(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Driver>> getAllDrivers() {
    return ResponseEntity.ok(driverService.getAllDrivers());
  }
}
