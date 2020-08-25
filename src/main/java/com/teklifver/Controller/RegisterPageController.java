package com.teklifver.Controller;

import com.teklifver.Services.*;
import com.teklifver.data.AddressData;
import com.teklifver.data.UserData;
import com.teklifver.entity.*;
import com.teklifver.facade.AddressFacade;
import com.teklifver.facade.UserFacade;
import com.teklifver.form.CompanyRegisterForm;
import com.teklifver.form.IndividualRegisterForm;
import com.teklifver.form.PostForm;
import com.teklifver.form.TeklifForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(name = "register")
public class RegisterPageController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private TownService townService;

    @Autowired
    private PostService postService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TeklifService teklifService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private  SubCategoryService subCategoryService;
    @RequestMapping("/individual")
    public String getRegisterPage(Model model)
    {
        List<CityEntity> list=new ArrayList<>();
         Iterable<CityEntity>  cityEntities=cityService.findAll();
        for (CityEntity p:cityEntities)
        {
            list.add(p);
        }
        model.addAttribute("popUp",true);
        model.addAttribute("cities",list);
        model.addAttribute("individualRegisterForm", new IndividualRegisterForm());
        return "registerCompany";
    }

    @RequestMapping(value = "/saveIndividual", method = RequestMethod.POST)
    public String saveIndividual(@Valid IndividualRegisterForm individualRegisterForm,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request,Model model)
    {
       if (bindingResult.hasErrors())
       {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.IndividualRegisterForm", bindingResult);
            Iterable<TownEntity> townEntities = townService.findAll();
            Iterable<CityEntity> cityEntities = cityService.findAll();
            model.addAttribute("cities",cityEntities);
//            model.addAttribute("towns",townEntities);
            return "pages/personalRegister";
        }
     else {

            String email = individualRegisterForm.getMail();
            UserEntity userEntity = userService.findUserEntityByEmail(email);
            if (userEntity != null)
            {
                return "redirect:/personalRegister";
            }
            AddressData addressData = new AddressData();
            AddressFacade.populateAddressData(addressData,individualRegisterForm);

            addressData = addressService.save(addressData);

            UserData userData = new UserData();
            userData.setAddress_id(addressData.getAddressId());
            UserFacade.populateUserData(userData,individualRegisterForm);
            HttpSession session = request.getSession();
            userData = userService.savePersonal(userData);
            session.setAttribute("customer",userData);
            redirectAttributes.addFlashAttribute("globalMessage", new GlobalMessage("Siparis guncellendi"));
            return "redirect:/";
        }

    }
    @RequestMapping(value = "postShow/{id}",method = RequestMethod.GET)
    public String showProduct(@PathVariable Long id, Model model,HttpServletRequest request){
        PostEntity postEntity = postService.getPostEntity(id);
        PostEntity post = postService.findOne(id);
        UserEntity user = userService.findOne(Long.parseLong(postEntity.getUserId()));
        model.addAttribute("postUserId",user.getId());
        UserData userData = userService.isLogin(request);

        if (userData != null){
            UserEntity userEntity = userService.findOne(Long.parseLong(userData.getUserId()));
            model.addAttribute("user",userEntity);
            model.addAttribute("userType", userEntity.getUserType());
            model.addAttribute("isLogin" , true);
        }
        else{
            model.addAttribute("user",user);
            model.addAttribute("userType", user.getUserType());
            model.addAttribute("isLogin" , false);
        }
        if (userData != null){
         if (post.getUserId().equals(userData.getUserId())){
             model.addAttribute("control",true);
         }
         else{
             model.addAttribute("control",false);
         }
        }
        else{
            model.addAttribute("control", null);
        }
        CategoryEntity categoryEntity = categoryService.findCategoryEntityByCategoryId(postEntity.getCategoryId());
        SubCategoryEntity subCategoryEntity = subCategoryService.findSubCategoryBySubName(postEntity.getSubCategoryId());
        model.addAttribute("teklifForm", new TeklifForm());
        model.addAttribute("postId",id);
        model.addAttribute("customerData",userService.isLogin(request));
        model.addAttribute("post",postEntity);
        model.addAttribute("postForm",new PostForm());
        Iterable<ImageEntity> imageEntities = imageService.findAllById(post.getId());
        model.addAttribute("images",imageEntities);
        Iterable<TeklifEntity> teklifEntities = teklifService.findAllByPostId(id);
        model.addAttribute("teklifs", teklifEntities);
        model.addAttribute("teklifForm",new TeklifForm());
        model.addAttribute("categoryName",categoryEntity.getCategoryName());
        model.addAttribute("subCategoryName",subCategoryEntity.getSubCategoryName());
        return "pages/ilanDetay";
    }

    @RequestMapping(value = "/saveCompany",method = RequestMethod.POST)
    public String saveCompany(@Valid @ModelAttribute CompanyRegisterForm companyRegisterForm,BindingResult bindingResult,  HttpServletRequest request)
    {
        if (!bindingResult.hasErrors()){

            UserEntity userEntity = userService.findUserEntityByEmail(companyRegisterForm.getMail());
            if (userEntity != null){
                return  "redirect:/companyRegister";
            }
            UserData userData = companyService.saveCompany(companyRegisterForm);
            HttpSession session = request.getSession();
            session.setAttribute("customer",userData);
            return "redirect:/";
        }
        else {
            return "pages/companyRegister";
        }
    }

    @GetMapping("/companyRegister")
    public String companyRegister(Model model)
    {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        Iterable<CategoryEntity> categoryEntityIterable = categoryService.findAll();
        for (CategoryEntity categoryEntity : categoryEntityIterable)
        {
            categoryEntities.add(categoryEntity);
        }
        List<CityEntity> list=new ArrayList<>();
        Iterable<CityEntity>  cityEntities=cityService.findAll();
        Iterable<TownEntity>  townEntities = townService.findAll();
        for (CityEntity p:cityEntities)
        {
            list.add(p);
        }
        model.addAttribute("categories",categoryEntities);
        model.addAttribute("cities",list);
        model.addAttribute("towns",townEntities);
        model.addAttribute("companyRegisterForm",new CompanyRegisterForm());
        return "pages/companyRegister";
    }


    @GetMapping("/chooseRegister")
    public String chooseRegister(){
        return "pages/chooseRegisterPage";
    }
    @GetMapping("/personalRegister")
    public String personalRegister(Model model){

        Iterable<CityEntity>  cityEntities=cityService.findAll();
        Iterable<TownEntity> townEntities = townService.findAll();

        model.addAttribute("towns", townEntities);
        model.addAttribute("popUp",true);
        model.addAttribute("cities",cityEntities);
        model.addAttribute("individualRegisterForm", new IndividualRegisterForm());
        return "pages/personalRegister";
    }
//    @GetMapping("/town/{provinceId}")
//    @ResponseBody
//    public List<TownEntity> getTown(@PathVariable int provinceId)
//    {
//        return townService.getTownByProvinceId(provinceId);
//    }
//
//    @GetMapping("/district/{townId}")
//    @ResponseBody
//    public List<DistrictEntity> getDistrict(@PathVariable int townId)
//    {
//        return districtService.getDistrictByTownId(Integer.toString(townId));
//    }


}
