package com.teklifver.repository;

import com.teklifver.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {
       CategoryEntity findCategoryEntityBy(String categoryName);
       CategoryEntity findById(long id);
       CategoryEntity findCategoryEntityByCategoryName(String categoryId);

}
