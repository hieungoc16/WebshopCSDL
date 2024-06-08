package com.springboot.webshop.repositories;

import com.springboot.webshop.models.Feedback;
import com.springboot.webshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbacksRepository extends JpaRepository<Feedback, Integer> {
    @Query("SELECT feedback FROM Feedback feedback WHERE (feedback.product.id=?1)")
    public List<Feedback> findFeedbackByProductId(Integer id);
}
