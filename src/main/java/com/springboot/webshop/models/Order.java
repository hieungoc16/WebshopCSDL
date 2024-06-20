package com.springboot.webshop.models;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @Column(name = "order_id")
    private int order_id;

    @Column(name = "order_date")
    private Date order_date;

    @Column(name = "order_status")
    private String order_status;

    @Column(name = "order_address")
    private String order_address;

    @Column(name = "order_totalprice")
    private double order_totalprice;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private Users users;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public double getOrder_totalprice() {
        return order_totalprice;
    }

    public void setOrder_totalprice(double order_totalprice) {
        this.order_totalprice = order_totalprice;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
