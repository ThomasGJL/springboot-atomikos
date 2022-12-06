package com.example.springbootatomikos.controller;

import com.example.springbootatomikos.entity.Order;
import com.example.springbootatomikos.service.JtaService;
import com.example.springbootatomikos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    JtaService jtaService;

    @GetMapping("/getOrder")
    public Order getOrder(@RequestParam("id") int id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/addOrder")
    public void addOrder()
    {
        Order order = new Order();
        order.setUser_id("1");
        order.setCommodity_code("product-1");
        order.setCount(1);
        order.setMoney(5);

        orderService.addOrder(order);
    }

    @GetMapping("/placeOrder")
    public Map<String, Object> placeOrder() {

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            return jtaService.placeOrder();
        } catch (Exception e) {
            resultMap.put("state", "fail");
            resultMap.put("message", "分布式事务失败");
            return resultMap;
        }

    }

}
