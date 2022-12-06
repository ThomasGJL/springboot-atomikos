package com.example.springbootatomikos.dao.dao2;

import com.example.springbootatomikos.entity.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper {

    Stock getStockById(Integer id);

    void updateStock(Stock stock);
}
