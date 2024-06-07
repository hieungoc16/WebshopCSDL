package com.springboot.webshop.services;

import com.springboot.webshop.models.User;

public interface IUserService {
    User findByUserName(String username);
}
