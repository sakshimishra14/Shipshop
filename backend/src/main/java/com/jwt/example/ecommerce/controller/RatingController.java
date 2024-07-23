package com.jwt.example.ecommerce.controller;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.model.Rating;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.request.RatingRequest;
import com.jwt.example.ecommerce.service.rating.interfc.RatingServiceInterface;
import com.jwt.example.ecommerce.service.user.intef.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private UserService userService;
    private RatingServiceInterface ratingServices;

    public RatingController(UserService userService,RatingServiceInterface ratingServices) {
        this.ratingServices=ratingServices;
        this.userService=userService;
        // TODO Auto-generated constructor stub
    }
    @PostMapping("/create")
    public ResponseEntity<Rating> createRatingHandler(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        String token = "";
        if (jwt != null && jwt.startsWith("Bearer")) {
            //looking good
            token = jwt.substring(7);
        }
        User user=userService.findUserByProfilByJwt(token);
        Rating rating=ratingServices.createRating(req, user);
        return new ResponseEntity<>(rating, HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsReviewHandler(@PathVariable Long productId){

        List<Rating> ratings=ratingServices.getProductsRating(productId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }
}
