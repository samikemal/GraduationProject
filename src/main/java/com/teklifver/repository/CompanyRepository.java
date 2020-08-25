package com.teklifver.repository;

import com.teklifver.entity.CompanyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<CompanyEntity,Long> {

    CompanyEntity findAllByEmail(String email);
}
