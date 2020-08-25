package com.teklifver.Services;

import com.teklifver.entity.PostEntity;
import com.teklifver.entity.TeklifEntity;
import com.teklifver.entity.UserEntity;
import com.teklifver.form.TeklifForm;
import com.teklifver.repository.PostRepository;
import com.teklifver.repository.TeklifRepository;
import com.teklifver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeklifService {



    @Autowired
    TeklifRepository teklifRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public void save(Long id, TeklifForm teklifForm, UserEntity userEntity){
        TeklifEntity teklifEntity = new TeklifEntity();
        PostEntity postEntity = postRepository.getPostEntityById(id);

        teklifEntity.setUserId((userEntity.getId()));
        teklifEntity.setPostId(postEntity.getId());
        teklifEntity.setComment(teklifForm.getComment());
        teklifEntity.setPrice(teklifForm.getPrice());
        teklifEntity.setUserName(userEntity.getName());
        teklifRepository.save(teklifEntity);
    }

    Iterable<TeklifEntity> findAllByUserId(Long id){
        return   teklifRepository.findAllByUserId(id);
    }
    public Iterable<TeklifEntity> findAllByPostId(Long id){
        return teklifRepository.findAllByPostId(id);
    }

}
