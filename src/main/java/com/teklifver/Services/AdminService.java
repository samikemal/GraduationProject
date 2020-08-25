package com.teklifver.Services;

import com.teklifver.entity.AdminUserEntity;
import com.teklifver.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminUserEntity findAdminUserEntity(String username){
       //AdminUserEntity adminUserEntity = adminRepository.findAllByUsername(username);
       //return adminUserEntity;
    return  null;
    }

}
