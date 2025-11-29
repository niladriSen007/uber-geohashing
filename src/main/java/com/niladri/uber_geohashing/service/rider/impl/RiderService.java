package com.niladri.uber_geohashing.service.rider.impl;

import com.niladri.uber_geohashing.dto.RiderRequest;
import com.niladri.uber_geohashing.dto.RiderResponse;
import com.niladri.uber_geohashing.mapper.Mapper;
import com.niladri.uber_geohashing.model.Rider;
import com.niladri.uber_geohashing.repository.RiderRepository;
import com.niladri.uber_geohashing.service.rider.IRiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RiderService implements IRiderService {

    private final RiderRepository riderRepository;

    @Override
    public RiderResponse createRider(RiderRequest riderRequest) {

        boolean existsByEmail = riderRepository.existsByEmail(riderRequest.getEmail());
        if (existsByEmail) {
            throw new RuntimeException("Rider already exists");
        }
        Rider rider = Mapper.toRider(riderRequest);
        return Mapper.toRiderResponse(riderRepository.save(rider));
    }

    @Override
    public RiderResponse updateRider(Long id, RiderRequest riderRequest) {
        Rider rider = riderRepository.findById(id).orElseThrow(() -> new RuntimeException("Rider not found"));

        //when user is updating email, check if the new email already exists
        if (!rider.getEmail().equals(riderRequest.getEmail()) && riderRepository.existsByEmail(riderRequest.getEmail())) {
            throw new RuntimeException("Rider with email already exists");
        }

        Mapper.updateRiderFromRequest(rider, riderRequest);
        return Mapper.toRiderResponse(riderRepository.save(rider));

    }

    @Override
    public void deleteRiderById(Long id) {
        if (!riderRepository.existsById(id)) {
            throw new RuntimeException("Rider not found");
        }
        riderRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiderResponse> findRiderById(Long id) {
        return riderRepository.findById(id).map(Mapper::toRiderResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiderResponse> findAllRiders() {
        return riderRepository.findAll().stream().map(Mapper::toRiderResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiderResponse> findByEmail(String email) {
        return riderRepository.findByEmail(email).map(Mapper::toRiderResponse);
    }

}
