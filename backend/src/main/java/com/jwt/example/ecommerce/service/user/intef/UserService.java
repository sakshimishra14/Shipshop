package com.jwt.example.ecommerce.service.user.intef;


import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    User findUserById(Long userId) throws UserException;
    User findUserByProfilByJwt(String jwt) throws UserException;

    public List<User> findAllUsers();

}
