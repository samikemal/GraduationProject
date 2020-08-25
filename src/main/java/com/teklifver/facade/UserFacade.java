package com.teklifver.facade;

import com.teklifver.data.UserData;
import com.teklifver.form.IndividualRegisterForm;

public class UserFacade {

    public static void populateUserData(UserData userData, IndividualRegisterForm individualRegisterForm)
    {
        userData.setEmail(individualRegisterForm.getMail());
        userData.setLastName(individualRegisterForm.getLastName());
        userData.setName(individualRegisterForm.getName());
        userData.setPhoneNumber(individualRegisterForm.getPhoneNumber());
        userData.setPassword(individualRegisterForm.getPassword());
        userData.setCityName(individualRegisterForm.getCity());
        userData.setTownName(individualRegisterForm.getTown());
        userData.setAddressLine(individualRegisterForm.getLine());
        userData.setUserType("personal");
    }

}
