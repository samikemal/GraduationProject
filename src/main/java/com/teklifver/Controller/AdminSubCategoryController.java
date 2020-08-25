package com.teklifver.Controller;


import com.teklifver.Services.CategoryService;
import com.teklifver.Services.SubCategoryService;
import com.teklifver.entity.CategoryEntity;
import com.teklifver.entity.SubCategoryEntity;
import com.teklifver.form.SubCategoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AdminSubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/saveSubCategory" , method = RequestMethod.POST)
    private String saveSubCategory(@Valid SubCategoryForm subCategoryForm){

        subCategoryService.saveSubCategory(subCategoryForm);
        return "redirect:/getSubCategory";
    }


    @RequestMapping(value = "editSubCategory/{id}", method = RequestMethod.POST)
    public String editSubCategory(@Valid SubCategoryForm subCategoryForm, @PathVariable Long id ){

        SubCategoryEntity subCategoryEntity = subCategoryService.findOne(id);
        if (subCategoryEntity != null && !subCategoryForm.getSubCategoryName().isEmpty()){
            subCategoryService.editSubCategory(subCategoryForm,subCategoryEntity);
        }
        return "redirect:/getSubCategory";
    }

    @RequestMapping( value = "getSubCategory" , method = RequestMethod.GET)
    public String getSubCategory(Model model){
        Iterable<SubCategoryEntity> subCategoryEntities = subCategoryService.findAll();
        Iterable<CategoryEntity> categoryEntities = categoryService.findAll();
        model.addAttribute("categories", categoryEntities);
        model.addAttribute("subCategories", subCategoryEntities);
        model.addAttribute("subCategoryForm",new SubCategoryForm());
        return "admin/subCategory";
    }


    @RequestMapping(value = "/deleteSubCategory/{id}",method = RequestMethod.GET)
    public String deleteSubCategory(@PathVariable Long id){
        subCategoryService.delete(id);
        return "redirect:/getSubCategory";
    }



}
