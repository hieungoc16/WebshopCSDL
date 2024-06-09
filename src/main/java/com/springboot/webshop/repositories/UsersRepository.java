package com.springboot.webshop.repositories;


import com.springboot.webshop.models.Product;
import com.springboot.webshop.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

    @Query("SELECT user FROM Users user ORDER BY user.id ASC")
    public List<Users> findAllUsers();

}
