
package com.teklifver.Controller;



import com.teklifver.Services.UserService;
import com.teklifver.data.UserData;
import com.teklifver.entity.CompanyEntity;
import com.teklifver.entity.PostEntity;
import com.teklifver.entity.UserEntity;
import com.teklifver.form.LoginForm;
import com.teklifver.form.SendEmailForm;
import com.teklifver.repository.CompanyRepository;
import com.teklifver.repository.PostRepository;
import com.teklifver.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/chooseLogin")
    public String chooseLogin(){
        return "pages/chooseLoginPage";
    }
    @GetMapping("/personalLogin")
    public String personalLogin(Model model){

        model.addAttribute("loginForm",new LoginForm());
        return "pages/personalLogin";
    }
    @GetMapping("/forgotPassword")
    public String forgotPassword( Model model){

        model.addAttribute("sendEmailForm",new SendEmailForm());
        return "pages/forgotPasswordPage";
    }

    @PostMapping("/individualLogin")
    private String Login(@ModelAttribute LoginForm loginForm, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {

        UserEntity userEntity = userService.findAllByEmail(loginForm.getMail());

        if (userEntity != null &&  userEntity.getPassword() != null &&  userEntity.getPassword().equals(loginForm.getPassword()))
        {
            UserData userData = userService.populateUserDat(userEntity);
            HttpSession session = request.getSession();
            session.setAttribute("customer",userData);
            if (userEntity.getUserType().equals("company"))
            {
                CompanyEntity companyEntity = companyRepository.findAllByEmail(loginForm.getMail());
                List<PostEntity> postEntityList = postRepository.findAllByTownIdAndCategoryId(companyEntity.getTownId(),companyEntity.getCategoryId());
                redirectAttributes.addFlashAttribute("posts",postEntityList);
            }
            return "redirect:/";
        }
        return "redirect:/personalLogin";
    }
//    @GetMapping("/district/{townId}")
//    @ResponseBody
//    public List<DistrictEntity> getDistrict(@PathVariable int townId)
//    {
//        return districtService.getDistrictByTownId(townId);
//    }
}
