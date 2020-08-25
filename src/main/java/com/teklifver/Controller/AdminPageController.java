package com.teklifver.Controller;

import com.teklifver.Services.AdminService;
import com.teklifver.entity.AdminUserEntity;
import com.teklifver.form.AdminLoginForm;
import com.teklifver.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AdminPageController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String registerPage(Model model){
        model.addAttribute("adminLoginForm",new AdminLoginForm());
        return "admin/adminLogin";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String adminLogin(@Valid @ModelAttribute AdminLoginForm adminLoginForm, Model model , HttpServletRequest request){


        AdminUserEntity adminUserEntity = adminRepository.findByUsername(adminLoginForm.getUsername());
        if (adminUserEntity != null &&  adminUserEntity.getPassword().equals(adminLoginForm.getPassword()))
        {
            return "redirect:/getCategory";
        }
        else {
            return "admin/adminLogin";
        }
    }










}