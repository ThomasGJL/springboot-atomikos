package com.example.springbootatomikos.dao.dao1;

import com.example.springbootatomikos.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    Order getOrderById(Integer id);

    int addOrder(Order order);

}
