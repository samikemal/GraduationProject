package com.teklifver.Controller;

import com.teklifver.Services.UserService;
import com.teklifver.data.UserData;
import com.teklifver.entity.UserEntity;
import com.teklifver.form.LoginForm;
import com.teklifver.form.TeklifForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ilanDetayController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/chooseLogin/{id}" , method = RequestMethod.GET)
    public String saveTeklif(@PathVariable Long id, @ModelAttribute TeklifForm teklifForm, Model model){
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("postId",id);
        return "pages/postLogin";
    }

    @RequestMapping(value = "/logOut/{id}" , method = RequestMethod.GET)
    public String posLogOut(@PathVariable Long id, @ModelAttribute TeklifForm teklifForm, Model model,HttpServletRequest request){
        model.addAttribute("loginForm", new LoginForm());
        HttpSession session = request.getSession();
        session.setAttribute("customer",null);
        model.addAttribute("postId",id);
        return "redirect:/postShow/{id}";
    }

    @RequestMapping(value = "/individualLogin/{id}" , method = RequestMethod.POST)
    public String postLogin(@PathVariable Long id, @ModelAttribute LoginForm loginForm, Model model, HttpServletRequest request){

        UserEntity userEntity = userService.findAllByEmail(loginForm.getMail());

        if (userEntity != null &&  userEntity.getPassword() != null &&  userEntity.getPassword().equals(loginForm.getPassword()))
        {
            UserData userData = userService.populateUserDat(userEntity);
            HttpSession session = request.getSession();
            session.setAttribute("customer",userData);
            model.addAttribute("postId",id);
            return "redirect:/postShow/{id}";
        }
        return "redirect:/chooselogin/{id}";
    }

}
