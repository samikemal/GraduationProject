package com.teklifver.Services;


import com.teklifver.data.UserData;
import com.teklifver.entity.AddressEntity;
import com.teklifver.entity.PostEntity;
import com.teklifver.form.PostForm;
import com.teklifver.repository.AddressRepository;
import com.teklifver.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FileServie fileServie;

    public Iterable<PostEntity> findAll(){
        return postRepository.findAll();
    }

    public void save(PostForm postForm, HttpServletRequest request)
    {
        UserData userData = userService.isLogin(request);
        AddressEntity addressEntity = addressRepository.findOne(userData.getAddress_id());
        PostEntity postEntity = new PostEntity();
        postEntity.setUserId(userData.getUserId());
        postEntity.setCategoryId(postForm.getCategoryId());
        postEntity.setSubCategoryId(postForm.getSubCategoryId());
        postEntity.setDescription(postForm.getDescription());
        postEntity.setTitle(postForm.getTitle());
        postEntity.setCityId(addressEntity.getCityName());
        postEntity.setAddressLine(addressEntity.getLine());
        postEntity.setTownId(userData.getTownName());
        postEntity.setCityId(userData.getCityName());
        postEntity.setStatus(1);
        postEntity.setAddressId(userData.getAddress_id());
        postRepository.save(postEntity);
        fileServie.fileSaveToFolder(postForm.getFile(),postEntity.getId());
    }

    public List<PostEntity> getPostByCateGoryId(String categoryId)
    {
        return postRepository.getAllByCategoryId(categoryId);
    }

    public PostEntity getPostEntity(long id){
        return postRepository.getPostEntityById(id);
    }

    public void updatePostEntity(PostForm postForm ,PostEntity postEntity){
        if (!postForm.getTitle().isEmpty()){
            postEntity.setTitle(postForm.getTitle());
        }
        if (!postForm.getDescription().isEmpty()){
            postEntity.setDescription(postForm.getDescription());
        }
        if (!postForm.getCategoryId().isEmpty()){
            postEntity.setCategoryId(postForm.getCategoryId());
            if (!postForm.getSubCategoryId().isEmpty()){
                postEntity.setSubCategoryId(postForm.getSubCategoryId());
            }
        }
        postRepository.save(postEntity);
    }
    public  PostEntity findOne(Long id){
        return postRepository.findOne(id);
    }

    public Iterable<PostEntity> findAllByUserId(String id){
        return postRepository.findAllByUserId(id);
    }
}
