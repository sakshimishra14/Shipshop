package com.jwt.example.ecommerce.service.rating.interfc;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.model.Rating;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.request.RatingRequest;

import java.util.List;

public interface RatingServiceInterface {
    public Rating createRating(RatingRequest req, User user) throws ProductException;

    public List<Rating> getProductsRating(Long productId);

}
