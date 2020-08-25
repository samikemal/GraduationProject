package com.teklifver.Services;

import com.teklifver.entity.ImageEntity;
import com.teklifver.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Iterable<ImageEntity> findAllById(Long id){
        return imageRepository.findAllByPostId(id);
    }

}
