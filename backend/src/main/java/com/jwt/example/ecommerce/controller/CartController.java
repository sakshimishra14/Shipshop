package com.jwt.example.ecommerce.controller;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.model.Cart;
import com.jwt.example.ecommerce.model.CartItem;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.request.AddItemRequest;
import com.jwt.example.ecommerce.response.ApiResponse;
import com.jwt.example.ecommerce.service.cart.interfc.CartServiceInterface;
import com.jwt.example.ecommerce.service.user.intef.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartServiceInterface cartService;
    private UserService userService;

    public CartController(CartServiceInterface cartService,UserService userService) {
        this.cartService=cartService;
        this.userService=userService;
    }
    @GetMapping("/")
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException{
            String token = "";
            if (jwt != null && jwt.startsWith("Bearer")) {
                //looking good
                token = jwt.substring(7);
            }
            User user = userService.findUserByProfilByJwt(token);

            Cart cart = cartService.findUserCart(user);

            System.out.println("cart - " + cart.getUser().getEmail());

            return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        String token = "";
        if (jwt != null && jwt.startsWith("Bearer")) {
            //looking good
            token = jwt.substring(7);
        }
        User user=userService.findUserByProfilByJwt(token);

        CartItem item = cartService.addCartItem(user.getId(), req);

        ApiResponse res= new ApiResponse("Item Added To Cart Successfully",true);

        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);

    }

}
