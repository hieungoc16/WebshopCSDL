package com.springboot.webshop.services;

import com.springboot.webshop.models.Feedback;

import java.util.List;

public interface IFeedbackService {
    List<Feedback> findFeedbacksByProductId(int productId);
}
