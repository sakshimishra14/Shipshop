package com.jwt.example.ecommerce.controller;

import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.service.user.intef.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {

        System.out.println("/api/users/profile");
        String token="";
        if (jwt != null && jwt.startsWith("Bearer")) {
            //looking good
            token = jwt.substring(7);
        }
            User user=userService.findUserByProfilByJwt(token);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }
}
