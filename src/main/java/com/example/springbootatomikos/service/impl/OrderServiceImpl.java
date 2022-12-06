package com.example.springbootatomikos.service.impl;

import com.example.springbootatomikos.dao.dao1.OrderMapper;
import com.example.springbootatomikos.entity.Order;
import com.example.springbootatomikos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }
}
