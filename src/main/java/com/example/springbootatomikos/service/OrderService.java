package com.example.springbootatomikos.service;

import com.example.springbootatomikos.entity.Order;

public interface OrderService {

    Order getOrderById(Integer id);

    void addOrder(Order order);

}
