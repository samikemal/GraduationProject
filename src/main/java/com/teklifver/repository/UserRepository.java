package com.teklifver.repository;



import com.teklifver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
    UserEntity findAllByEmail(String eMail);
    UserEntity findUserEntityByEmail(String email);
    UserEntity findUserEntityById(Long id);
    List<UserEntity> findAll();

}
