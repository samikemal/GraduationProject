package com.teklifver.repository;

import com.teklifver.entity.DenemeEntity;
import com.teklifver.entity.DenemeFiles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenemeFileRepository extends CrudRepository<DenemeFiles,Long> {

    List<DenemeFiles> getDenemeFilesByDenemeEntity(DenemeEntity denemeEntity);
}
