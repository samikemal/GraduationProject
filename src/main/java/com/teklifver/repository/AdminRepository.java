package com.teklifver.repository;

import com.teklifver.entity.AdminUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminUserEntity, Long> {

    AdminUserEntity findByUsername(String username);

}
