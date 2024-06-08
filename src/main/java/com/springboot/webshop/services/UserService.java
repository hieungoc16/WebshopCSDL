package com.springboot.webshop.services;

import com.springboot.webshop.models.Users;
import com.springboot.webshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Users findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
