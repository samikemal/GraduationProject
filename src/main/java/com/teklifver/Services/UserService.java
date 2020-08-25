package com.teklifver.Services;

import com.teklifver.data.UserData;
import com.teklifver.entity.TeklifEntity;
import com.teklifver.entity.UserEntity;
import com.teklifver.form.CheckPasswordForm;
import com.teklifver.form.UserUyeForm;
import com.teklifver.repository.TeklifRepository;
import com.teklifver.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeklifRepository teklifRepository;

    public UserEntity findOne(Long id){
        return userRepository.findOne(id);
    }

    public UserEntity findUserEntityByEmail(String email){
        return userRepository.findUserEntityByEmail(email);
    }

    public UserEntity findAllByEmail(String email){
        return  userRepository.findUserEntityByEmail(email);
    }
    public void editPassword(String mail, CheckPasswordForm checkPasswordForm){
        UserEntity userEntity = userRepository.findAllByEmail(mail);
        userEntity.setPassword(checkPasswordForm.getPassword());
        userRepository.save(userEntity);
    }
    public UserEntity findUserEntityById(Long id){
        return userRepository.findUserEntityById(id);
    }

    public void editAddress (Long id , UserUyeForm userUyeForm) {
        if (!userUyeForm.getCityName().isEmpty() && !userUyeForm.getTownName().isEmpty()
                && !userUyeForm.getLine().isEmpty()){
            UserEntity userEntity = userRepository.findOne(id);
            userEntity.setCityName(userUyeForm.getCityName());
            userEntity.setTownName(userUyeForm.getTownName());
            userEntity.setAddressLine(userUyeForm.getLine());
            userRepository.save(userEntity);
        }
    }
    public void editProfile(Long id,UserUyeForm userUyeForm) {
            UserEntity userEntity = userRepository.findOne(id);
            UserEntity user = userRepository.findUserEntityByEmail(userUyeForm.getMail());
            try {

            if (user == null){
            user = geteditProfile(id,userEntity,userUyeForm);
            user.setEmail(userUyeForm.getMail());
            userRepository.save(user);
             }
        else {
             user =  geteditProfile(id,userEntity,userUyeForm);
             if( user != null){
                 userRepository.save(user);
             }
            }
            }catch (Exception ex){
                LOGGER.error("ex"  + ex.getMessage());
            }


    }

    public UserData savePersonal(UserData userData)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress_id(userData.getAddress_id());
        userEntity.setEmail(userData.getEmail());
        userEntity.setLastName(userData.getLastName());
        userEntity.setName(userData.getName());
        userEntity.setPassword(userData.getPassword());
        userEntity.setPhoneNumber(userData.getPhoneNumber());
        userEntity.setCityName(userData.getCityName());
        userEntity.setTownName(userData.getTownName());
        userEntity.setAddressLine(userData.getAddressLine());
        userEntity.setUserType(userData.getUserType());
        userRepository.save(userEntity);
        userData.setUserId(userEntity.getId().toString());
        return userData;
    }
    public UserData saveCompany(UserData userData)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress_id(userData.getAddress_id());
        userEntity.setEmail(userData.getEmail());
        userEntity.setLastName(userData.getLastName());
        userEntity.setName(userData.getName());
        userEntity.setPassword(userData.getPassword());
        userEntity.setPhoneNumber(userData.getPhoneNumber());
        userEntity.setCityName(userData.getCityName());
        userEntity.setTownName(userData.getTownName());
        userEntity.setAddressLine(userData.getAddressLine());
        userEntity.setUserType("company");

        userRepository.save(userEntity);
        userData.setUserId(userEntity.getId().toString());
        return userData;
    }



    public UserData isLogin(HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        if (session.getAttribute("customer") != null)
        {
            UserData userData = (UserData) session.getAttribute("customer");
            return userData;
        }

        return null;
    }

    public UserData populateUserDat(UserEntity userEntity)
    {
        UserData userData = new UserData();
        userData.setUserId(userEntity.getId().toString());
        userData.setPhoneNumber(userEntity.getPhoneNumber());
        userData.setEmail(userEntity.getEmail());
        userData.setName(userEntity.getName());
        userData.setLastName(userEntity.getLastName());
        userData.setCityName(userEntity.getCityName());
        userData.setTownName(userEntity.getTownName());
        userData.setAddress_id(userEntity.getAddress_id());
        return userData;
    }

    public  UserEntity geteditProfile(Long id, UserEntity userEntity, UserUyeForm userUyeForm){
            if (!userUyeForm.getLastname().isEmpty()) {
                userEntity.setLastName(userUyeForm.getLastname());


            }
            if (!userUyeForm.getName().isEmpty()) {
                userEntity.setName(userUyeForm.getName());
                Iterable<TeklifEntity> teklifEntities = teklifRepository.findAllByUserId(id);
                for (TeklifEntity teklifEntity : teklifEntities){
                    teklifEntity.setUserName(userUyeForm.getName());
                    teklifRepository.save(teklifEntity);

                }
            }
        return userEntity;
    }


}
