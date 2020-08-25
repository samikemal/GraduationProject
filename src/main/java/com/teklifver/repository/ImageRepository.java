package com.teklifver.repository;

import com.teklifver.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity,Long>
{
     Iterable<ImageEntity>  findAllByPostId(Long postId);
}