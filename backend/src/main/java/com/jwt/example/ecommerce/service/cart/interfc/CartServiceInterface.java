package com.jwt.example.ecommerce.service.cart.interfc;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.model.Cart;
import com.jwt.example.ecommerce.model.CartItem;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.request.AddItemRequest;

public interface CartServiceInterface {
    public Cart createCart(User user);

    public CartItem addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(User user);

}
