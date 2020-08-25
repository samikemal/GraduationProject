package com.teklifver.Controller;

import com.teklifver.Services.*;
import com.teklifver.data.PostData;
import com.teklifver.data.UserData;
import com.teklifver.entity.*;
import com.teklifver.repository.CategoryRepository;
import com.teklifver.repository.ImageRepository;
import com.teklifver.repository.PostRepository;
import com.teklifver.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxPostController {

    @Autowired
    CityService cityService;

    @Autowired
    TownService townService;

    @Autowired
    private PostService postService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/town/{cityName}")
    @ResponseBody
    public List<TownEntity> getTown(@PathVariable String cityName)
    {
        CityEntity cityEntity = cityService.findByCityName(cityName);
        return townService.getTownByProvinceId(cityEntity.getId().toString());
    }

    @GetMapping("/category/{categoryId}")
    public String getPost(@PathVariable int categoryId, Model model, HttpServletRequest httpServletRequest)
    {
        List<PostEntity> postEntities = postService.getPostByCateGoryId(Integer.toString(categoryId));
        model.addAttribute("posts",postEntities);
        List<SubCategoryEntity> subCategoryEntities = subCategoryService.getSubCategoryByCategoryId(categoryId);
        model.addAttribute("subCategories",subCategoryEntities);
        return "CategoryPage";
    }

    @GetMapping("/subCategory/{categoryId}")
    @ResponseBody
    public List<SubCategoryEntity> getSubCategory(@PathVariable int categoryId)
    {
        return subCategoryService.getSubCategoryByCategoryId(categoryId);
    }

    @GetMapping("/cat/{categoryName}")
    public String getCategoryPage(@PathVariable String categoryName,Model model,HttpServletRequest request)
    {
        Boolean isLogin = false;
        if ((userService.isLogin(request) != null))
        {
            UserData userData = userService.isLogin(request);
            UserEntity userEntity = userService.findUserEntityByEmail(userData.getEmail());
            isLogin = true;
            model.addAttribute("customerData",userService.isLogin(request));
            model.addAttribute("userType" , userEntity.getUserType());

        }
        model.addAttribute("isLogin",isLogin);
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByCategoryName(categoryName);
        List<SubCategoryEntity> subCategoryEntityList = subCategoryService.getSubCategoryByCategoryId(Math.toIntExact(categoryEntity.getId()));
        List<PostEntity> postEntities = postService.getPostByCateGoryId(Integer.toString(Math.toIntExact(categoryEntity.getId())));
        model.addAttribute("posts",populatePostData(postEntities));
        model.addAttribute("subCategories",subCategoryEntityList);
        return "pages/CategoryPage";
    }
    @GetMapping("/sub/{categoryName}/{subCategoryName}")
    public String getSubCategoryPage(@PathVariable String subCategoryName,@PathVariable String categoryName,Model model,HttpServletRequest request)
    {
        Boolean isLogin = false;
        if ((userService.isLogin(request) != null))
        {
            UserData userData = userService.isLogin(request);
            UserEntity userEntity = userService.findUserEntityByEmail(userData.getEmail());
            isLogin = true;
            model.addAttribute("customerData",userService.isLogin(request));
            model.addAttribute("userType" , userEntity.getUserType());

        }
        model.addAttribute("isLogin",isLogin);
        SubCategoryEntity subCategoryEntity = subCategoryRepository.findBySubCategoryName(subCategoryName);
        List<PostEntity> postEntities = postRepository.getAllBySubCategoryId(Integer.toString(Math.toIntExact(subCategoryEntity.getId())));
        model.addAttribute("posts",populatePostData(postEntities));

        return "pages/SubCategoryPage";
    }

    private List<PostEntity> populatePostData(List<PostEntity> postEntities)
    {
        List<PostData> postDataList = new ArrayList<>();

        for (PostEntity postEntity : postEntities)
        {
            List<ImageEntity> imageEntities = (List<ImageEntity>) imageRepository.findAllByPostId(postEntity.getId());

            if (imageEntities.size() != 0)
            {
                postEntity.setFileName(imageEntities.get(0).getFileName());
            }
        }

        return postEntities;
    }


}
