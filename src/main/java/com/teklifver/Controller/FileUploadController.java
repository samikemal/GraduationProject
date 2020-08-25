package com.teklifver.Controller;


import com.teklifver.Services.DenemeService;
import com.teklifver.entity.DenemeEntity;
import com.teklifver.entity.DenemeFiles;
import com.teklifver.repository.DenemeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    @Autowired
    private DenemeService denemeService;

    @Autowired
    private DenemeFileRepository denemeFileRepository;


    @GetMapping(value = "/fileUpload")
    private String fileUpload(Model model)
    {
        model.addAttribute("denemeForm",new DenemeEntity());
        return "FileUpload";
    }

    @PostMapping(value = "/saveDeneme")
    private String saveDeneme(@ModelAttribute DenemeEntity denemeEntity, RedirectAttributes redirectAttributes)
    {

        DenemeEntity denemeEntity1 = denemeService.save(denemeEntity);

        if (denemeEntity1 != null)
        {

            Iterable<DenemeFiles> denemeFiles = denemeFileRepository.getDenemeFilesByDenemeEntity(denemeEntity1);

            List<DenemeFiles> denemeFiles1 = new ArrayList<>();
            for (DenemeFiles denemeFiles2 : denemeFiles)
            {
                denemeFiles1.add(denemeFiles2);
            }
            redirectAttributes.addFlashAttribute("files",denemeFiles1);
            redirectAttributes.addFlashAttribute("message","success");
            return "redirect:/fileUpload";
        }
        return null;
    }
}
