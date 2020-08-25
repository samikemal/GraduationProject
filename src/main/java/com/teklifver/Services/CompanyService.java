package com.teklifver.Services;

import com.teklifver.data.UserData;
import com.teklifver.entity.CompanyEntity;
import com.teklifver.form.CompanyRegisterForm;
import com.teklifver.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;

    public UserData saveCompany(CompanyRegisterForm companyRegisterForm){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCategoryId(companyRegisterForm.getCategoryId());
        companyEntity.setCompanyName(companyRegisterForm.getCompanyName());
        companyEntity.setEmail(companyRegisterForm.getMail());
        companyEntity.setPhoneNumber(companyRegisterForm.getPhoneNumber());
        companyEntity.setSubCategoryId(companyRegisterForm.getSubCategoryId());
        companyEntity.setCityId(companyRegisterForm.getCity());
        companyEntity.setTownId(companyRegisterForm.getTown());

        companyEntity.setPassword(companyRegisterForm.getPassword());

        UserData userData = new UserData();
        companyRepository.save(companyEntity);
        userData.setCityName(companyRegisterForm.getCity());
        userData.setTownName(companyRegisterForm.getTown());
        userData.setAddressLine(companyRegisterForm.getLine());
        userData.setName(companyEntity.getCompanyName());
        userData.setEmail(companyEntity.getEmail());
        userData.setPhoneNumber(companyEntity.getPhoneNumber());
        userData.setPassword(companyRegisterForm.getPassword());
        userService.saveCompany(userData);
        return userData;
    }
}
