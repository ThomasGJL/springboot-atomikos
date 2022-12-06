package com.example.springbootatomikos.service.impl;

import com.example.springbootatomikos.dao.dao2.StockMapper;
import com.example.springbootatomikos.entity.Stock;
import com.example.springbootatomikos.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockMapper stockMapper;

    @Override
    public Stock getStockById(Integer id) {
        return stockMapper.getStockById(id);
    }

    @Override
    @Transactional
    public void updateStock(Stock stock) {

        stockMapper.updateStock(stock);
    }



}
