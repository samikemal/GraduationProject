package com.teklifver.repository;

import com.teklifver.entity.TeklifEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeklifRepository extends JpaRepository<TeklifEntity,Long>
{
         Iterable<TeklifEntity> findAllByPostId(Long id);
         Iterable<TeklifEntity> findAllByUserId(Long id);
         @Query("SELECT t.userName FROM TeklifEntity t where t.userId = :id") // TODO: Şuanlık kullanılmıyor ama ornek olarak dursun elimizde
         List<String> findUserNameByUserId(@Param("id") Long id);
}
