package com.jwt.example.ecommerce.service.order.interfc;

import com.jwt.example.ecommerce.Exception.OrderException;
import com.jwt.example.ecommerce.model.Address;
import com.jwt.example.ecommerce.model.Order;
import com.jwt.example.ecommerce.model.User;

import java.util.List;

public interface OrderServiceInterface {
    public Order createOrder(User user, Address shippingAdress);

    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId)throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancledOrder(Long orderId) throws OrderException;

    public List<Order>getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;

}
