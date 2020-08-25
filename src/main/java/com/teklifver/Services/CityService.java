package com.teklifver.Services;

import com.teklifver.entity.CityEntity;
import com.teklifver.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public Iterable<CityEntity> findAll(){
        return cityRepository.findAll();
    }
    public CityEntity findByCityName(String cityName){
        return cityRepository.findByCityName(cityName);
    }
}
