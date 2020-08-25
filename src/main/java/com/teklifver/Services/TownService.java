package com.teklifver.Services;


import com.teklifver.entity.TownEntity;
import com.teklifver.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;

    public Iterable<TownEntity> findAll(){
        return townRepository.findAll();
    }
    public List<TownEntity> getTownByProvinceId(String provinceId)
    {
        return townRepository.getAllByProvinceId(provinceId);
    }
}
