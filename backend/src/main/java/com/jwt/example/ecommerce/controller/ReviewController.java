package com.jwt.example.ecommerce.controller;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.model.Review;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.request.ReviewRequest;
import com.jwt.example.ecommerce.service.review.interfc.ReviewServiceInterface;
import com.jwt.example.ecommerce.service.user.intef.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController

{
    private ReviewServiceInterface reviewService;
    private UserService userService;

    public ReviewController(ReviewServiceInterface reviewService,UserService userService) {
        this.reviewService=reviewService;
        this.userService=userService;
        // TODO Auto-generated constructor stub
    }
    @PostMapping("/create")
    public ResponseEntity<Review> createReviewHandler(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        String token = "";
        if (jwt != null && jwt.startsWith("Bearer")) {
            //looking good
            token = jwt.substring(7);
        }
        User user=userService.findUserByProfilByJwt(token);
        System.out.println("product id "+req.getProductId()+" - "+req.getReview());
        Review review=reviewService.createReview(req, user);
        System.out.println("product review "+req.getReview());
        return new ResponseEntity<Review>(review, HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReviewHandler(@PathVariable Long productId){
        List<Review>reviews=reviewService.getAllReview(productId);
        return new ResponseEntity<List<Review>>(reviews,HttpStatus.OK);
    }

}
