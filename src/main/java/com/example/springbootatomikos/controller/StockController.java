package com.example.springbootatomikos.controller;

import com.example.springbootatomikos.entity.Stock;
import com.example.springbootatomikos.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/getStock")
    public Stock getStock(@RequestParam("id") int id){
        return stockService.getStockById(id);
    }
}
