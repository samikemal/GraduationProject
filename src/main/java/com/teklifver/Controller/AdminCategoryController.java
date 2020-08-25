package com.teklifver.Controller;

import com.teklifver.Services.CategoryService;
import com.teklifver.data.UserData;
import com.teklifver.entity.CategoryEntity;
import com.teklifver.form.CategoryForm;
import com.teklifver.form.UserUyeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "editCategory/{id}", method = RequestMethod.POST)
    public String advisermentInformation(@Valid CategoryForm categoryForm, @PathVariable Long id, Model model){
        CategoryEntity categoryEntity = categoryService.findOne(id);
        if (!categoryForm.getCategoryName().isEmpty() ){
            categoryService.edit(categoryEntity,categoryForm);
        }
        return "redirect:/getCategory";
    }

    @RequestMapping(value = "deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id){

        if (id != 0 ){
            categoryService.delete(id);
        }
        return "redirect:/getCategory";

    }


    @RequestMapping(value = "/saveCategory",method = RequestMethod.POST)
    public String save(@Valid CategoryForm categoryForm)  {
        if (!categoryForm.getCategoryName().isEmpty()){
            categoryService.save(categoryForm);
        }
        return "redirect:/getCategory";
    }

    @RequestMapping(value = "/getCategory", method = RequestMethod.GET)
    private String getCategory(Model model){
        Iterable<CategoryEntity> categoryEntities = categoryService.findAll();
        model.addAttribute("categories", categoryEntities);
        model.addAttribute("userUyeForm", new UserUyeForm());
        model.addAttribute("categoryForm",new CategoryForm());
        return "admin/admin";
    }

    @GetMapping(value = "ilanDetay")
    public String a(){
        return "admin/ilanDetayDeneme";
    }
}
