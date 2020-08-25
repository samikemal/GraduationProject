package com.teklifver.repository;

import com.teklifver.entity.SubCategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubCategoryRepository extends CrudRepository<SubCategoryEntity,Long> {

     List<SubCategoryEntity> getSubCategoryEntitiesByCategoryId(String categoryId);
     Iterable<SubCategoryEntity> findAllByCategoryName(String categoryName);
     SubCategoryEntity findBySubCategoryName(String subCategoryName);
     SubCategoryEntity findSubCategoryEntityBySubCategoryName(String subCategoryName);
     SubCategoryEntity findById(Long id);
}
