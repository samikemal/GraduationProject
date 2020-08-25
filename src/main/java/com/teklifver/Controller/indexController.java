package com.teklifver.Controller;


import com.teklifver.Services.CategoryService;
import com.teklifver.Services.PostService;
import com.teklifver.Services.SubCategoryService;
import com.teklifver.Services.UserService;
import com.teklifver.data.PostData;
import com.teklifver.data.UserData;
import com.teklifver.entity.CategoryEntity;
import com.teklifver.entity.ImageEntity;
import com.teklifver.entity.PostEntity;
import com.teklifver.entity.UserEntity;
import com.teklifver.form.SirketUyeForm;
import com.teklifver.form.UserUyeForm;
import com.teklifver.repository.CategoryRepository;
import com.teklifver.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class indexController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public String gotoIndex(HttpServletRequest request,Model model)
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
        Iterable<CategoryEntity> categoryEntityIterable = categoryService.findAll();
        List<CategoryEntity> categoryEntities = new ArrayList<>();


        for (CategoryEntity categoryEntity : categoryEntityIterable)
        {
            categoryEntities.add(categoryEntity);
        }

        Iterable<PostEntity> postEntities = postService.findAll();

        model.addAttribute("posts",populatePostData((List<PostEntity>) postEntities));
        model.addAttribute("categories",categoryEntities);
        return "index2";
    }

    @GetMapping("/logOut")
    public String logOut(Model model,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.setAttribute("customer",null);

        return "redirect:/";
    }

//    @GetMapping("/category/{categoryId")
//    @ResponseBody
//    public String getPost(@PathVariable("categoryId") int categoryId, RedirectAttributes redirectAttributes)
//    {
//        List<PostEntity> postEntities = postService.getPostByCateGoryId(Integer.toString(categoryId));
//        redirectAttributes.addFlashAttribute("posts",postEntities);
//        List<SubCategoryEntity> subCategoryEntities = subCategoryService.getSubCategoryByCategoryId(categoryId);
//        redirectAttributes.addFlashAttribute("subCategories",subCategoryEntities);
//        return "redirect:/";
//    }



//    @RequestMapping("/")
//    public String goruntule(Model model,HttpServletRequest request){
//        HttpSession session=request.getSession();
//        request.getSession().getAttribute("loginorexit");
//        session.setAttribute("loginorexit","GirisYap");
//        String login=(String)request.getSession().getAttribute("loginorexit");
//        model.addAttribute("logins",login);
//        model.addAttribute("isim",session.getAttribute("username"));
//        return "index2";
//    }
//    @RequestMapping("/login")
//    public  String login(Model model)
//    {
//        model.addAttribute("loginForm",new LoginForm());
//        return "Login";
//    }
    @RequestMapping("/Uye")
    public String Uye(Model model)
    {
        model.addAttribute("useruyeform", new UserUyeForm());
        model.addAttribute("sirketuyeform",new SirketUyeForm());
        return "UyeOl";
    }
//    @RequestMapping("/bireysel")
//    public String Bireysel(Model model)
//    {
//        List<Province> list=new ArrayList<>();
//        Iterable<Province>  provinces=provinceReporsitory.findAll();
//        for (Province p:provinces)
//        {
//            list.add(p);
//        }
//        model.addAttribute("provinces",list);
//        model.addAttribute("useruyeform", new UserUyeForm());
//        return "register";
//    }
//    @RequestMapping("/kurumsal")
//    public String Kurumsal(Model model)
//    {
//        List<Province> list=new ArrayList<>();
//        Iterable<Province>  provinces=provinceReporsitory.findAll();
//        for (Province p:provinces)
//        {
//            list.add(p);
//        }
//        model.addAttribute("provinces",list);
//        model.addAttribute("sirketuyeform", new SirketUyeForm());
//        return "UyeSirket";
//    }
//    @RequestMapping("/ilanver")
//   public  String ilanver(Model model)
//    {
//        model.addAttribute("ilanform",new IlanForm());
//        return "/ilanver";
//    }

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

//    @GetMapping("/category/{categoryId}")
//    @ResponseBody
//    public List<SubCategoryEntity> getPost(@PathVariable int categoryId)
//    {
//        return subCategoryService.getSubCategoryByCategoryId(categoryId);
//    }









}
