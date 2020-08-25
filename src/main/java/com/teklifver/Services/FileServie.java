package com.teklifver.Services;


import com.teklifver.entity.ImageEntity;
import com.teklifver.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileServie {


    @Autowired
    private ImageRepository imageRepository;
    private final Path rootLocation = Paths.get("src/main/resources/static/img");

    public void fileSaveToFolder(List<MultipartFile> files,long postId)
    {
        for (MultipartFile file : files)
        {
            try {
                Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setPostId(postId);
                imageEntity.setFileName(file.getOriginalFilename());
                imageRepository.save(imageEntity);
            } catch (Exception e) {
                throw new RuntimeException("FAIL! -> message = " + e.getMessage());
            }
        }
    }
}
