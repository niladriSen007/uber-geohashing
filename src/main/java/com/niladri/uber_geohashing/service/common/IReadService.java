package com.niladri.uber_geohashing.service.common;

import java.util.List;
import java.util.Optional;

public interface IReadService<T, ID> {
    Optional<T> findById(ID id);

    List<T> findAll();
}
