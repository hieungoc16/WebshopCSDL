package com.springboot.webshop.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "users_username")
    private String username;
    @Column(name = "users_password")
    private String passWord;
    @Column(name = "users_kind")
    private Boolean kind;
    @Column(name = "users_name")
    private String name;
    @Column(name = "users_dob")
    private Date dob;
    @Column(name = "users_address")
    private String address;
    @Column(name = "users_mail")
    private String mail;
    @Column(name = "users_phone")
    private String phone;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;
    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Users(Integer id, String username, String passWord, Boolean kind, String name, Date dob, String address, String mail, String phone, Set<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.passWord = passWord;
        this.kind = kind;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.userRoles = userRoles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Boolean getKind() {
        return kind;
    }

    public void setKind(Boolean kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
