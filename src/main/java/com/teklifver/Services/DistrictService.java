package com.teklifver.Services;


import com.teklifver.entity.DistrictEntity;
import com.teklifver.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;


    public List<DistrictEntity> getDistrictByTownId(int townId)
    {
        return districtRepository.getAllByTownId(Integer.toString(townId));
    }
}
