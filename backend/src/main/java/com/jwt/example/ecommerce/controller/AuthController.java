package com.jwt.example.ecommerce.controller;

import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.config.JwtHelper;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.repository.UserRepository;
import com.jwt.example.ecommerce.request.LoginRequest;
import com.jwt.example.ecommerce.response.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtHelper jwtHelper;
    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtHelper jwtHelper) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtHelper = jwtHelper;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        logger.info("Login request for email: {}", request.getEmail());
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        AuthResponse response = AuthResponse.builder()
                .jwt(token)
                .status(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User request) throws UserException {
        String email = request.getEmail();
        String password = request.getPassword();
        String firstName=request.getFirstName();
        String lastName=request.getLastName();

        User isEmailExist=userRepository.findByEmail(email);
        if (isEmailExist!=null) {

            throw new UserException("Email Is Already Used With Another Account");
        }
        // Create new user
        User createdUser= new User();
        createdUser.setEmail(email);
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        createdUser.setPassword(passwordEncoder.encode(password));
        User savedUser= userRepository.save(createdUser);

        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        AuthResponse response = AuthResponse.builder()
                .jwt(token)
                .status(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        logger.info("Authenticating email: {}", email);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)

    public String exceptionHandler(BadCredentialsException e) {
        logger.error("Authentication exception: {}", e.getMessage());
        return "Credentials Invalid !!";
    }

}
