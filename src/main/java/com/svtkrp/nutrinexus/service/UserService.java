package com.svtkrp.nutrinexus.service;

import com.svtkrp.nutrinexus.domain.UserModel;
import com.svtkrp.nutrinexus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel add() {
        UserModel userModel = new UserModel();
        userModel.setName("Joe");
        userModel.setEmail("joe2@mail.com");
        return userRepository.save(userModel);
    }
}
