package com.jwt.example.ecommerce.controller;

import com.jwt.example.ecommerce.Exception.CartItemException;
import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.model.CartItem;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.response.ApiResponse;
import com.jwt.example.ecommerce.service.cartitems.interfc.CartItemInterface;
import com.jwt.example.ecommerce.service.user.intef.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")

public class CartItemController {
    private CartItemInterface cartItemService;
    private UserService userService;

    public CartItemController(CartItemInterface cartItemService,UserService userService) {
        this.cartItemService=cartItemService;
        this.userService=userService;
    }
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartItemHandler(@PathVariable Long cartItemId, @RequestHeader("Authorization")String jwt) throws CartItemException, UserException{

        String token = "";
        if (jwt != null && jwt.startsWith("Bearer")) {
            //looking good
            token = jwt.substring(7);
        }
        User user = userService.findUserByProfilByJwt(token);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res=new ApiResponse("Item Remove From Cart",true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(@PathVariable Long cartItemId, @RequestBody CartItem cartItem, @RequestHeader("Authorization")String jwt) throws CartItemException, UserException {
        String token = "";
        if (jwt != null && jwt.startsWith("Bearer")) {
            //looking good
            token = jwt.substring(7);
        }
        User user=userService.findUserByProfilByJwt(token);

        CartItem updatedCartItem =cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

        //ApiResponse res=new ApiResponse("Item Updated",true);

        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
    }

}
