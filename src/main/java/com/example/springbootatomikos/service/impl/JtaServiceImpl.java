package com.example.springbootatomikos.service.impl;

import com.example.springbootatomikos.entity.Order;
import com.example.springbootatomikos.entity.Stock;
import com.example.springbootatomikos.service.JtaService;
import com.example.springbootatomikos.service.OrderService;
import com.example.springbootatomikos.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class JtaServiceImpl implements JtaService {

    @Autowired
    OrderService orderService;

    @Autowired
    StockService stockService;

    @Override
    @Transactional(transactionManager = "xatx", propagation = Propagation.REQUIRED, rollbackFor = {java.lang.RuntimeException.class})
    public Map<String, Object> placeOrder() {

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();

        Order order = new Order();
        order.setUser_id("1");
        order.setCommodity_code("product-1");
        order.setCount(1);
        order.setMoney(5);

        orderService.addOrder(order);

        Stock stock = new Stock();
        stock = stockService.getStockById(1);
        int stockNum = stock.getCount();
        int newstockNum = stockNum - 1;

        Stock newstock = new Stock();
        newstock.setId(stock.getId());
        newstock.setCommodity_code(stock.getCommodity_code());
        newstock.setCount(newstockNum);
        stockService.updateStock(newstock);

        resultMap.put("state", "success");
        resultMap.put("message", "分布式事务成功");

        return resultMap;
    }
}
