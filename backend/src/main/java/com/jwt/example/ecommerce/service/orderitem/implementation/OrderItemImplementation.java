package com.jwt.example.ecommerce.service.orderitem.implementation;

import com.jwt.example.ecommerce.model.OrderItem;
import com.jwt.example.ecommerce.repository.OrderItemRepository;
import com.jwt.example.ecommerce.service.orderitem.interfc.OrderItemInterface;
import org.springframework.stereotype.Service;

@Service
public class OrderItemImplementation implements OrderItemInterface {
    private OrderItemRepository orderItemRepository;
    public OrderItemImplementation(OrderItemRepository orderItemRepository) {
        this.orderItemRepository=orderItemRepository;
    }
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
