package com.springboot.webshop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Column(name = "feedback_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedback_id;

    @Column(name = "feedback_date")
    private String feedback_date;

    @Column(name = "feedback_note")
    private String feedback_note;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private Users users;

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getFeedback_date() {
        return feedback_date;
    }

    public void setFeedback_date(String feedback) {
        this.feedback_date = feedback;
    }

    public String getFeedback_note() {
        return feedback_note;
    }

    public void setFeedback_note(String feedback_note) {
        this.feedback_note = feedback_note;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Users getUsers_id() {
        return users;
    }

    public void setUsers_id(Users users_id) {
        this.users = users_id;
    }
}
