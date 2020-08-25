package com.teklifver.repository;

import com.teklifver.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hasan on 24.04.2018.
 */
public interface CityRepository extends CrudRepository<CityEntity,Long> {

    CityEntity findById(long id);
    CityEntity findByCityName(String cityName);
}
