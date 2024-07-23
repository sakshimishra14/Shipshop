package com.jwt.example.ecommerce.service.cart.implementation;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.model.Cart;
import com.jwt.example.ecommerce.model.CartItem;
import com.jwt.example.ecommerce.model.Product;
import com.jwt.example.ecommerce.model.User;
import com.jwt.example.ecommerce.repository.CartRepository;
import com.jwt.example.ecommerce.request.AddItemRequest;
import com.jwt.example.ecommerce.service.cart.interfc.CartServiceInterface;
import com.jwt.example.ecommerce.service.cartitems.interfc.CartItemInterface;
import com.jwt.example.ecommerce.service.product.iface.ProductServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartServiceInterface {
    private CartRepository cartRepository;
    private CartItemInterface cartItemService;
    private ProductServiceInterface productService;


    public CartServiceImplementation(CartRepository cartRepository,CartItemInterface cartItemService,
                                     ProductServiceInterface productService) {
        this.cartRepository=cartRepository;
        this.productService=productService;
        this.cartItemService=cartItemService;
    }
    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        Cart createdCart=cartRepository.save(cart);
        return createdCart;
    }

    @Override
    public CartItem addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setId(userId);
            cart = cartRepository.save(cart);  // Save the newly created cart
        }

        // Fetch the product to be added to the cart
        Product product = productService.findProductById(req.getProductId());
        if (product == null) {
            throw new ProductException("Product not found with id: " + req.getProductId());
        }

        // Check if the item is already present in the cart
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
        if (isPresent == null) {
            // Item not present, create a new cart item
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);
            int price = req.getQuantity() * product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            // Create the cart item and add it to the cart
            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
            cartRepository.save(cart);  // Save the cart with the new item
            return createdCartItem;
        }
        // Item already present in the cart, return the existing item
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        try {
            // Fetch the cart associated with the user
            Cart cart = cartRepository.findByUserId(user.getId());
            int totalPrice = 0;
            int totalDiscountedPrice = 0;
            int totalItem = 0;

            if (cart != null) {
                // Calculate the totals from the existing cart items
                for (CartItem cartItem : cart.getCartItems()) {
                    totalPrice += cartItem.getPrice();
                    totalDiscountedPrice += cartItem.getDiscountedPrice();
                    totalItem += cartItem.getQuantity();
                }
            } else {
                // Create a new cart if none exists
                cart = new Cart();
                cart.setUser(user);
            }

            // Set the calculated values
            cart.setTotalPrice(totalPrice);
            cart.setTotalItem(totalItem);
            cart.setTotalDiscountedPrice(totalDiscountedPrice);
            cart.setDiscounte(totalPrice - totalDiscountedPrice);

            // Save the cart and return it
            return cartRepository.save(cart);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
