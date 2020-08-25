package com.teklifver.repository;

import com.teklifver.entity.DistrictEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistrictRepository extends CrudRepository<DistrictEntity,Long> {

    public List<DistrictEntity> getAllByTownId(String townId);
}
