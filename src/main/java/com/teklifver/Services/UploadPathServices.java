package com.teklifver.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;

@Service
public class UploadPathServices {

    @Autowired
    private ServletContext servletContext;


    public File getFilePath(String modifiedFileName, String path) {

        boolean exist = new File(servletContext.getRealPath("/" + path + "/")).exists();

        if (!exist)
        {
            new File(servletContext.getRealPath("/" + path + "/")).mkdir();
        }
        String modifiedFilePath = servletContext.getRealPath("/" + path + "/" + File.separator + modifiedFileName);

        File file = new File(modifiedFilePath);

        return file;
    }
}
