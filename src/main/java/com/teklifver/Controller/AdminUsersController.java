package com.teklifver.Controller;

import com.teklifver.entity.UserEntity;
import com.teklifver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminUsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "getUsers",method = RequestMethod.GET)
    public String getAdminUsersPage(Model model)
    {
        List<UserEntity> userEntityList = userRepository.findAll();
        model.addAttribute("users",userEntityList);
        return "admin/adminUsersPage";
    }

    @RequestMapping(value = "/deleteUsers/{id}",method = RequestMethod.GET)
    public String deleteSubCategory(@PathVariable Long id){
        userRepository.delete(id);
        return "redirect:/getUsers";
    }

}
