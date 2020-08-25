package com.teklifver.Controller;


import com.teklifver.Services.PostService;
import com.teklifver.Services.UserService;
import com.teklifver.data.UserData;
import com.teklifver.entity.CategoryEntity;
import com.teklifver.form.CategoryForm;
import com.teklifver.form.PostForm;
import com.teklifver.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping
    private String createPost(Model model, RedirectAttributes redirectAttributes,HttpServletRequest request)
    {

        UserData userData = userService.isLogin(request);
        if (userData == null)
        {
            redirectAttributes.addFlashAttribute("message","Hasan");
            return "redirect:/personalLogin";
        }

        List<CategoryEntity> categoryEntities = new ArrayList<>();
        Iterable<CategoryEntity> categoryEntityIterable = categoryRepository.findAll();
        for (CategoryEntity categoryEntity : categoryEntityIterable)
        {
            categoryEntities.add(categoryEntity);
        }
        model.addAttribute("categories",categoryEntities);
        model.addAttribute("postForm",new PostForm());

        redirectAttributes.addFlashAttribute("message","success");
        return "createPostPage";
    }


    @PostMapping("/createPost")
    private String create(@ModelAttribute PostForm postForm,HttpServletRequest request)
    {
        postService.save(postForm,request);
        return "redirect:/";
    }
    @GetMapping("/userAdvisermentList")
    public String userAdvisermentList(Model model){
        model.addAttribute("categoryForm",new CategoryForm());
        return "pages/userAdvisermentList";
    }

}
