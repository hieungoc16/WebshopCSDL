package com.springboot.webshop.services;

import com.springboot.webshop.models.Feedback;
import com.springboot.webshop.repositories.FeedbacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService implements IFeedbackService {
    @Autowired
    private FeedbacksRepository feedbackRepository;

    public List<Feedback> findFeedbacksByProductId(int id){
        return feedbackRepository.findFeedbackByProductId(id);
    }
}
