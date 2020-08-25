package com.teklifver.Controller;

import com.teklifver.Services.PostService;
import com.teklifver.Services.TeklifService;
import com.teklifver.Services.UserService;
import com.teklifver.data.UserData;
import com.teklifver.entity.PostEntity;
import com.teklifver.entity.UserEntity;
import com.teklifver.form.TeklifForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeklifVerController {

    @Autowired
    private TeklifService teklifService;
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/saveTeklif/{id}" , method = RequestMethod.POST)
    public String saveTeklif(@PathVariable Long id, @ModelAttribute TeklifForm teklifForm, HttpServletRequest request){
        UserData userData = userService.isLogin(request);
        if (userData != null){
            UserEntity userEntity = userService.findUserEntityById(Long.parseLong(userData.getUserId()));
            teklifService.save(id,teklifForm,userEntity);
        }

        return "redirect:/postShow/{id}";

    }

    @RequestMapping(value = "adviserment/{id}" , method = RequestMethod.GET)
    public String userAdvisermentList(@PathVariable Long id, Model model, HttpServletRequest request){

        UserData userData = userService.isLogin(request);
        UserEntity userEntity  = userService.findUserEntityById(id);
        if (userData != null){
            if (userData.getUserId().equals(String.valueOf(userEntity.getId()))){
                model.addAttribute("myUserId",userEntity.getId());
                Iterable<PostEntity> postEntities = postService.findAllByUserId(String.valueOf(id));
                model.addAttribute("user",userEntity);
                model.addAttribute("posts", postEntities);
                return "pages/userAdvisermentList";
            }

        }
        return "redirect:/";
    }
}
