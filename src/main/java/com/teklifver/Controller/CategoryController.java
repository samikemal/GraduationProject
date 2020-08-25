package com.teklifver.Controller;

import com.teklifver.Services.PostService;
import com.teklifver.Services.SubCategoryService;
import com.teklifver.Services.UserService;
import com.teklifver.repository.CategoryRepository;
import com.teklifver.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

//    @GetMapping("/category/{categoryName}")
//    public String getSubCategories(@PathVariable String categoryName, Model model, HttpServletRequest request)
//    {
//
//        Boolean isLogin = false;
//        if ((userService.isLogin(request) != null))
//        {
//            isLogin = true;
//            model.addAttribute("customerData",userService.isLogin(request));
//        }
//        model.addAttribute("isLogin",isLogin);
//
//        List<PostEntity> postEntities = postService.getPostByCateGoryId(Integer.toString(categoryId));
//        model.addAttribute("posts",postEntities);
//
//        CategoryEntity categoryEntity = categoryRepository.findById(categoryId);
//        List<SubCategoryEntity> subCategoryEntities = (List<SubCategoryEntity>) subCategoryRepository.findAllByCategoryName(categoryEntity.getCategoryName());
//        model.addAttribute("subCategories",subCategoryEntities);
//        model.addAttribute("categoryName",categoryEntity.getCategoryName());
//        return "pages/CategoryPage";
//    }








}
