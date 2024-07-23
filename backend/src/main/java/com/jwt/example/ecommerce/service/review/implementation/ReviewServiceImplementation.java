package com.jwt.example.ecommerce.service.review.implementation;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.model.Product;
import com.jwt.example.ecommerce.model.Review;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.repository.ProductRepository;
import com.jwt.example.ecommerce.repository.ReviewRepository;
import com.jwt.example.ecommerce.request.ReviewRequest;
import com.jwt.example.ecommerce.service.product.iface.ProductServiceInterface;
import com.jwt.example.ecommerce.service.review.interfc.ReviewServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewServiceInterface {
    private ReviewRepository reviewRepository;
    private ProductServiceInterface productService;
    private ProductRepository productRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository,ProductServiceInterface productService,ProductRepository productRepository) {
        this.reviewRepository=reviewRepository;
        this.productService=productService;
        this.productRepository=productRepository;
    }

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product=productService.findProductById(req.getProductId());
        Review review=new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
//		product.getReviews().add(review);
        productRepository.save(product);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
