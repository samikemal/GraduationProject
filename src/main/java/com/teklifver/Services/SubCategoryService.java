package com.teklifver.Services;


import com.teklifver.entity.CategoryEntity;
import com.teklifver.entity.SubCategoryEntity;
import com.teklifver.form.SubCategoryForm;
import com.teklifver.repository.CategoryRepository;
import com.teklifver.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<SubCategoryEntity> getSubCategoryByCategoryId(int categoryId)
    {
        return subCategoryRepository.getSubCategoryEntitiesByCategoryId(Integer.toString(categoryId));
    }

    public  SubCategoryEntity findSubCategoryBySubName(String subCategoryId){
        return subCategoryRepository.findById(Long.parseLong(subCategoryId));
    }
    public void editSubCategory(SubCategoryForm subCategoryForm , SubCategoryEntity subCategoryEntity ){
            subCategoryEntity.setSubCategoryName(subCategoryForm.getSubCategoryName());
            subCategoryRepository.save(subCategoryEntity);
    }
    public  SubCategoryEntity findOne(Long id){
        return  subCategoryRepository.findOne(id);
    }
    public SubCategoryEntity findSubCategoryById(String subCategoryId){
         return  subCategoryRepository.findSubCategoryEntityBySubCategoryName(subCategoryId);
    }
    public void saveSubCategory(SubCategoryForm subCategoryForm){
        if(!subCategoryForm.getSubCategoryName().isEmpty()){
            CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByCategoryName(subCategoryForm.getCategoryName());
            SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
            subCategoryEntity.setCategoryName(subCategoryForm.getCategoryName());
            subCategoryEntity.setSubCategoryName(subCategoryForm.getSubCategoryName());
            subCategoryEntity.setCategoryId(Long.toString(categoryEntity.getId()));
            subCategoryRepository.save(subCategoryEntity);
        }
    }
    public  void delete(Long id){
        SubCategoryEntity subCategoryEntity = subCategoryRepository.findOne(id);
        subCategoryRepository.delete(subCategoryEntity);
    }

    public Iterable<SubCategoryEntity> findAll(){
        return subCategoryRepository.findAll();
    }
}
