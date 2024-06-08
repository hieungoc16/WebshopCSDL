package com.springboot.webshop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "roles_id", referencedColumnName = "roles_id")
    private Roles roles;

    public UserRole() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserRole(Long id, Users user, Roles role) {
        this.id = id;
        this.users = user;
        this.roles = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users user) {
        this.users = user;
    }

    public Roles getRole() {
        return roles;
    }

    public void setRole(Roles role) {
        this.roles = role;
    }
}
