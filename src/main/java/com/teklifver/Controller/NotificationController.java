package com.teklifver.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NotificationController {

    @RequestMapping(value = "notifications/{id}",method = RequestMethod.GET)
    public String notifications(@PathVariable Long id, Model model)
    {
        model.addAttribute("id" ,id);
        return "pages/notifications";
    }
}
