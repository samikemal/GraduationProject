package com.teklifver.Services;

import com.teklifver.entity.CategoryEntity;
import com.teklifver.entity.SubCategoryEntity;
import com.teklifver.form.CategoryForm;
import com.teklifver.repository.CategoryRepository;
import com.teklifver.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public Iterable<CategoryEntity> findAll(){
        return categoryRepository.findAll();
    }

    public CategoryEntity findCategoryEntityByCategoryId(String categoryId){
        return categoryRepository.findById(Long.parseLong(categoryId));
    }
    public  void edit(CategoryEntity categoryEntity, CategoryForm categoryForm){
        Iterable<SubCategoryEntity> subCategoryEntities = subCategoryRepository.findAllByCategoryName(categoryEntity.getCategoryName());
        if (subCategoryEntities != null){
            for (SubCategoryEntity subCategoryEntity : subCategoryEntities){
                subCategoryEntity.setCategoryName(categoryForm.getCategoryName());
                subCategoryRepository.save(subCategoryEntity);
            }
        }
        categoryEntity.setCategoryName(categoryForm.getCategoryName());
        categoryRepository.save(categoryEntity);
    }

    public void delete(Long id){
        categoryRepository.delete(id);
    }

    public CategoryEntity findOne(Long id ) {
        return categoryRepository.findOne(id);
    }

    public void save(CategoryForm categoryForm){
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByCategoryName(categoryForm.getCategoryName());
        if (categoryEntity == null){
            CategoryEntity category = new CategoryEntity();
            category.setCategoryName(categoryForm.getCategoryName());
            categoryRepository.save(category);
        }
    }

}
