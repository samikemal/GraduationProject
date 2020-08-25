package com.teklifver.Controller;


import com.teklifver.Services.*;
import com.teklifver.data.UserData;
import com.teklifver.entity.*;
import com.teklifver.form.LoginForm;
import com.teklifver.form.PostForm;
import com.teklifver.form.UserUyeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyAccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private TownService townService;

    @Autowired
    private CityService cityService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @RequestMapping(value = "editProfile/{id}", method = RequestMethod.POST)
    public String editProfile(@Valid UserUyeForm userUyeForm, @PathVariable Long id) {
        userService.editProfile(id,userUyeForm);
        return "redirect:/myAccount/{id}";
    }

    @RequestMapping(value = "editAddress/{id}", method = RequestMethod.POST)
    public String editAddress(@Valid UserUyeForm userUyeForm,@PathVariable Long id){
        userService.editAddress(id,userUyeForm);
        return "redirect:/myAccount/{id}";
    }


    @RequestMapping(value = "myAccount/{id}",method = RequestMethod.GET)
    public String myAccount(@PathVariable Long id, Model model, HttpServletRequest request){

        UserData userData = userService.isLogin(request);
        UserEntity userEntity = userService.findOne(id);
        if(userData != null){
            if( userData.getUserId().equals(String.valueOf(userEntity.getId()))){
                model.addAttribute("userControl" , true);
            }
            else
            {
                model.addAttribute("userControl",false);
            }
            model.addAttribute("myUserId",userData.getUserId());

        }
        else {
            model.addAttribute("userControl",null);
        }
        Iterable<PostEntity> postEntities = postService.findAll();
        List<PostEntity> postEntityList = new ArrayList<>();
        for (PostEntity postEntity : postEntities){
            if(postEntity.getUserId().equals(String.valueOf(id))){
                postEntityList.add(postEntity);
            }
        }
        if(postEntityList.isEmpty()){
            model.addAttribute("postIsEmpty", true);
        }
        Iterable<CityEntity> cityEntities = cityService.findAll();
        Iterable<TownEntity> townEntities = townService.findAll();

        model.addAttribute("userType", userEntity.getUserType());
        model.addAttribute("posts", postEntityList);
        model.addAttribute("towns",townEntities);
        model.addAttribute("cities",cityEntities);
        model.addAttribute("id",id);
        model.addAttribute("user",userEntity);
        model.addAttribute("postForm",new PostForm());
        model.addAttribute("userUyeForm", new UserUyeForm());
        return "pages/myAccount";
    }

    @RequestMapping(value = "/myAccountLogin/{id}" , method = RequestMethod.GET)
    public String accountLog(@PathVariable Long id, Model model){
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("userId",id);
        return "pages/myAccountLogin";
    }


    @RequestMapping(value = "/myAccountLogin/{id}" , method = RequestMethod.POST)
    public String accountLogin(@PathVariable Long id, @ModelAttribute LoginForm loginForm, Model model, HttpServletRequest request){

        UserEntity userEntity = userService.findAllByEmail(loginForm.getMail());

        if (userEntity != null &&  userEntity.getPassword() != null &&  userEntity.getPassword().equals(loginForm.getPassword()))
        {
            UserData userData = userService.populateUserDat(userEntity);
            HttpSession session = request.getSession();
            session.setAttribute("customer",userData);
            model.addAttribute("postId",id);
            return "redirect:/myAccount/{id}";
        }
        return "redirect:/myAccountLogin/{id}";

    }
    @RequestMapping(value = "/logoutMyAccount/{id}" , method = RequestMethod.GET)
    public String posLogOut(@PathVariable Long id, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("customer",null);
        return "redirect:/myAccount/{id}";
    }
    @GetMapping(value = "advisermentInformation/{id}")
    public String advisermentInformation(@PathVariable Long id, Model model, HttpServletRequest request){
            UserData userData = userService.isLogin(request);
            PostEntity postEntity = postService.getPostEntity(id);
            Iterable<CategoryEntity> categoryEntities = categoryService.findAll();
            Iterable<SubCategoryEntity> subCategoryEntities = subCategoryService.findAll();
            UserEntity userEntity = userService.findUserEntityById(Long.parseLong(postEntity.getUserId()));

        if (userData == null || !userData.getUserId().equals(String.valueOf(userEntity.getId()))){
            return "redirect:/postShow/{id}";
        }
        if (userData.getUserId().equals(userEntity.getId().toString())) {
            model.addAttribute("owner", true);
            model.addAttribute("categories",categoryEntities);
            model.addAttribute("subCategories",subCategoryEntities);
        }
        else {
            List<CategoryEntity> categoryEntities1 = new ArrayList<>();
            model.addAttribute("owner",false);
            model.addAttribute("categories",categoryEntities1);
        }
        model.addAttribute("user",userEntity);
        model.addAttribute("postForm",new PostForm());
        model.addAttribute("post",postEntity);
        model.addAttribute("myUserId",userEntity.getId());
        model.addAttribute("userControl",true);
        return "pages/advisertmentInformation";
    }
    @PostMapping(value = "/editPost/{id}")
    public String editPost(@ModelAttribute PostForm postForm , @PathVariable Long id,  HttpServletRequest request){

        UserData userData = userService.isLogin(request);
        PostEntity postEntity = postService.getPostEntity(id);
        if (userData.getUserId().equals(postEntity.getUserId())) {
            postService.updatePostEntity(postForm,postEntity);
        }
        // postService.updatePostEntity(postForm,request);
        return "redirect:/advisermentInformation/{id}";
    }
}
