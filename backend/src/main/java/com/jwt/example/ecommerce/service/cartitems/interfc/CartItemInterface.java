package com.jwt.example.ecommerce.service.cartitems.interfc;

import com.jwt.example.ecommerce.Exception.CartItemException;
import com.jwt.example.ecommerce.Exception.UserException;
import com.jwt.example.ecommerce.model.Cart;
import com.jwt.example.ecommerce.model.CartItem;
import com.jwt.example.ecommerce.model.Product;

public interface CartItemInterface {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
