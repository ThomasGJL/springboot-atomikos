package com.example.springbootatomikos.service;

import com.example.springbootatomikos.entity.Stock;

public interface StockService {

    Stock getStockById(Integer id);

    void updateStock(Stock stock);
}
