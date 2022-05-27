package com.example.laba.controller;

import com.example.laba.calculations.CalculationsPrediction;
import com.example.laba.cache.Cache;
import com.example.laba.stat.Statistics;
import com.example.laba.stat.StatProv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    public static final StatProv statProv = new StatProv();
    public StatisticsController(){}
    @GetMapping
    public ResponseEntity<Statistics> getAllStats() {
        statProv.calculate();
        return new ResponseEntity<>(statProv.getStats(), HttpStatus.OK);
    }

}
