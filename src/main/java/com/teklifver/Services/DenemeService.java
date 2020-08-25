package com.teklifver.Services;


import com.teklifver.entity.DenemeEntity;
import com.teklifver.entity.DenemeFiles;
import com.teklifver.repository.DenemeFileRepository;
import com.teklifver.repository.DenemeRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DenemeService {

    @Autowired
    private DenemeRepository denemeRepository;
    @Autowired
    private UploadPathServices uploadPathServices;

    @Autowired
    private DenemeFileRepository denemeFileRepository;

    private final Path rootLocation = Paths.get("src/main/img");

    public DenemeEntity save(DenemeEntity denemeEntity)
    {
        DenemeEntity db = denemeRepository.save(denemeEntity);

        if (db != null && denemeEntity.getFiles() != null && denemeEntity.getFiles().size()>0)
        {
            for (MultipartFile file : denemeEntity.getFiles())
            {
                String fileName = file.getOriginalFilename();
                String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
                File storeFile = uploadPathServices.getFilePath(modifiedFileName,"img");

                if (storeFile != null)
                {
                    try {
                        FileUtils.writeByteArrayToFile(storeFile,file.getBytes());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                try {
                    Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
                } catch (Exception e) {
                    throw new RuntimeException("FAIL! -> message = " + e.getMessage());
                }

                DenemeFiles files = new DenemeFiles();
                files.setDenemeEntity(db);
                files.setFileExtension(FilenameUtils.getExtension(fileName));
                files.setFileName(fileName);
                files.setModifiedFileName(modifiedFileName);
                denemeFileRepository.save(files);
            }
        }

        return db;
    }
}
