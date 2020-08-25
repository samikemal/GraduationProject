package com.teklifver.repository;

import com.teklifver.entity.TownEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TownRepository extends CrudRepository<TownEntity,Long> {

    public List<TownEntity> getAllByProvinceId(String provinceId);

    TownEntity findByTownName(String townName);
}
