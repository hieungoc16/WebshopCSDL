package com.springboot.webshop.services;

import com.springboot.webshop.models.Users;

import java.util.List;

public interface IUserService {
    Users findByUserName(String username);

    List<Users> findAllUsers();
}
