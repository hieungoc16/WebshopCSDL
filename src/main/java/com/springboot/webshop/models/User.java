package com.springboot.webshop.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Column(name = "users_id")
    @Id
    private int user_id;

    @Column(name = "users_name")
    private String users_name;

    @Column(name = "users_username")
    private String users_username;

    @Column(name = "users_mail")
    private String users_mail;

    @Column(name = "users_phone")
    private String users_phone;

    @Column(name = "users_password")
    private String users_password;

    @Column(name = "users_address")
    private String users_address;

    @Column(name = "users_dob")
    private String users_dob;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getUsers_username() {
        return users_username;
    }

    public void setUsers_username(String users_username) {
        this.users_username = users_username;
    }

    public String getUsers_mail() {
        return users_mail;
    }

    public void setUsers_mail(String users_mail) {
        this.users_mail = users_mail;
    }

    public String getUsers_phone() {
        return users_phone;
    }

    public void setUsers_phone(String users_phone) {
        this.users_phone = users_phone;
    }

    public String getUsers_password() {
        return users_password;
    }

    public void setUsers_password(String users_password) {
        this.users_password = users_password;
    }

    public String getUsers_address() {
        return users_address;
    }

    public void setUsers_address(String users_address) {
        this.users_address = users_address;
    }

    public String getUsers_dob() {
        return users_dob;
    }

    public void setUsers_dob(String users_dob) {
        this.users_dob = users_dob;
    }
}
