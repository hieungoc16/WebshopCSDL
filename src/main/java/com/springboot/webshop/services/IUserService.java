package com.springboot.webshop.services;

import com.springboot.webshop.models.Users;

public interface IUserService {
    Users findByUserName(String username);
}
