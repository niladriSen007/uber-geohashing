package com.niladri.uber_geohashing.service.location;

import com.niladri.uber_geohashing.dto.DriverLocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private static final String DRIVER_GEO_KEY = "driver:geo";

    private final GeoOperations<String, String> geoOperations;
    private final StringRedisTemplate redisTemplate;

    public void updateDriverLocation(Long driverId, Double latitude, Double longitude) {
        GeoOperations<String, String> stringStringGeoOperations = redisTemplate.opsForGeo();
        stringStringGeoOperations.add(DRIVER_GEO_KEY, new GeoLocation<>(driverId.toString(), new Point(latitude, longitude)));
    }

    public List<DriverLocationRequest> getNearbyDrivers(Double latitude, Double longitude, Double radiusInKm) {
        GeoOperations<String, String> stringStringGeoOperations = redisTemplate.opsForGeo();
        Distance circleRadius = new Distance(radiusInKm, Metrics.KILOMETERS);
        Circle circle = new Circle(new Point(latitude, longitude), circleRadius);
        GeoResults<GeoLocation<String>> results = geoOperations.radius(DRIVER_GEO_KEY, circle); // Find drivers within the circle
        List<DriverLocationRequest> nearbyDrivers = new ArrayList<>();
        for (GeoResult<GeoLocation<String>> result : results) {
            Point point = geoOperations.position(DRIVER_GEO_KEY, result.getContent().getName()).get(0); // Get the exact position of the driver
            DriverLocationRequest build = DriverLocationRequest.builder()
                    .driverId(result.getContent().getName()) // driverId as String
                    .latitude(point.getX()) // Latitude
                    .longitude(point.getY()) // Longitude
                    .build();
            nearbyDrivers.add(build);
        }
        return nearbyDrivers;
    }

}
