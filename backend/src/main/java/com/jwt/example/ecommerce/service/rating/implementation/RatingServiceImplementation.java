package com.jwt.example.ecommerce.service.rating.implementation;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.model.Product;
import com.jwt.example.ecommerce.model.Rating;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.repository.RatingRepository;
import com.jwt.example.ecommerce.request.RatingRequest;
import com.jwt.example.ecommerce.service.product.iface.ProductServiceInterface;
import com.jwt.example.ecommerce.service.rating.interfc.RatingServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingServiceInterface {
    private RatingRepository ratingRepository;
    private ProductServiceInterface productService;

    public RatingServiceImplementation(RatingRepository ratingRepository,ProductServiceInterface productService) {
        this.ratingRepository=ratingRepository;
        this.productService=productService;
    }
    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product=productService.findProductById(req.getProductId());

        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
