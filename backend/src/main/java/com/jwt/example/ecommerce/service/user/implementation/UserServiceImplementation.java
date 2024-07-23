package com.jwt.example.ecommerce.service.user.implementation;

import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.config.JwtHelper;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.repository.UserRepository;
import com.jwt.example.ecommerce.service.user.intef.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private JwtHelper jwtTokenProvider;

    public UserServiceImplementation(UserRepository userRepository,JwtHelper jwtTokenProvider) {

        this.userRepository=userRepository;
        this.jwtTokenProvider=jwtTokenProvider;

    }
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user=userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with id "+userId);
    }

    @Override
    public User findUserByProfilByJwt(String jwt) throws UserException {
        System.out.println("user service");
        String email=jwtTokenProvider.getUsernameFromToken(jwt);

        System.out.println("email"+email);

        User user=userRepository.findByEmail(email);

        if(user==null) {
            throw new UserException("user not exist with email "+email);
        }
        System.out.println("email user"+user.getEmail());
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }
}
