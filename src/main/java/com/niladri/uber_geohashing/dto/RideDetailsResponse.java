package com.niladri.uber_geohashing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.niladri.uber_geohashing.model.RideDetails.RideStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideDetailsResponse {
  private Long id;
  private Long passengerId;
  private String passengerName;
  private Long driverId;
  private String driverName;
  private String pickupLocation;
  private String dropoffLocation;
  private RideStatus status;
  private Double fare;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime scheduledPickupTime;
  private LocalDateTime actualPickupTime;
  private LocalDateTime completedAt;
}
