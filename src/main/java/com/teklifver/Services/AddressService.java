package com.teklifver.Services;

import com.teklifver.data.AddressData;
import com.teklifver.entity.AddressEntity;
import com.teklifver.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    public AddressData save(AddressData addressData)
    {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setDistrictName(addressData.getDistrictName());
        addressEntity.setCityName(addressData.getCityName());
        addressEntity.setLine(addressData.getLine());
        addressEntity.setTownName(addressData.getTownName());

        addressRepository.save(addressEntity);

        addressData.setAddressId(addressEntity.getId());

        return addressData;

    }

}
