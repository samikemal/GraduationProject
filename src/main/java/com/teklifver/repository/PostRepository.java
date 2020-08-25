package com.teklifver.repository;

import com.teklifver.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {

    List<PostEntity> getAllByCategoryId(String categoryId);
    PostEntity getPostEntityById(long id);
    Iterable<PostEntity> findAllByUserId (String id);
    List<PostEntity> findAllByTownIdAndCategoryId(String townId,String categoryId);
    List<PostEntity> getAllBySubCategoryId(String subCategoryId);
}
