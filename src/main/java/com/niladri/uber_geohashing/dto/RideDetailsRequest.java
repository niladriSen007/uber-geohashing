package com.niladri.uber_geohashing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideDetailsRequest {
  @NotNull(message = "Passenger ID is required")
  private Long riderId;

  private Long driverId;

  @NotNull(message = "Pickup location lat is required")
  private String pickupLocationLatitude;

  @NotBlank(message = "Pickup location long is required")
  private String pickupLocationLongitude;

  private String dropoffLocationLatitude;

  private String dropoffLocationLongitude;

  private BigDecimal fare;

  private LocalDateTime scheduledPickupTime;
}
