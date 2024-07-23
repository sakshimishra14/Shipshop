package com.jwt.example.ecommerce.service.review.interfc;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.model.Review;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.request.ReviewRequest;

import java.util.List;

public interface ReviewServiceInterface {
    public Review createReview(ReviewRequest req, User user) throws ProductException;

    public List<Review> getAllReview(Long productId);

}
