package com.teklifver.facade;

import com.teklifver.data.AddressData;
import com.teklifver.form.IndividualRegisterForm;

public class AddressFacade {

    public static void populateAddressData(AddressData addressData , IndividualRegisterForm individualRegisterForm)
    {
        addressData.setCityName(individualRegisterForm.getCity());
        addressData.setTownName(individualRegisterForm.getTown());
        addressData.setLine(individualRegisterForm.getLine());
    }
}
