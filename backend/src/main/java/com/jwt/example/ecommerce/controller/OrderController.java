package com.jwt.example.ecommerce.controller;

import com.jwt.example.ecommerce.Exception.OrderException;
import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.model.Address;
import com.jwt.example.ecommerce.model.Order;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.service.order.interfc.OrderServiceInterface;
import com.jwt.example.ecommerce.service.user.intef.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderServiceInterface orderService;
    private UserService userService;

    public OrderController(OrderServiceInterface orderService,UserService userService) {
        this.orderService=orderService;
        this.userService=userService;
    }
    @PostMapping("/")
    public ResponseEntity<Order> createOrderHandler(@RequestBody Address spippingAddress,
                                                    @RequestHeader("Authorization")String jwt) throws UserException{

        String token = "";
        if (jwt != null && jwt.startsWith("Bearer")) {
            //looking good
            token = jwt.substring(7);
        }
        User user=userService.findUserByProfilByJwt(token);
        Order order =orderService.createOrder(user, spippingAddress);

        return new ResponseEntity<Order>(order,HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity< List<Order>> usersOrderHistoryHandler(@RequestHeader("Authorization")
                                                                 String jwt) throws OrderException, UserException{

        String token = "";
        if (jwt != null && jwt.startsWith("Bearer")) {
            //looking good
            token = jwt.substring(7);
        }
        User user=userService.findUserByProfilByJwt(token);
        List<Order> orders=orderService.usersOrderHistory(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")
    String jwt) throws OrderException, UserException {

        String token = "";
        if (jwt != null && jwt.startsWith("Bearer")) {
            //looking good
            token = jwt.substring(7);
        }
        User user=userService.findUserByProfilByJwt(token);
        Order orders=orderService.findOrderById(orderId);
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }
}
