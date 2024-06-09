package com.springboot.webshop.services;

import com.springboot.webshop.models.Users;
import com.springboot.webshop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService{
    @Autowired
    private UsersRepository userRepository;

    @Override
    public Users findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Users> findAllUsers(){
        return userRepository.findAllUsers();
    }
}
