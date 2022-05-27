package com.example.laba.controller;

import com.example.laba.calculations.CalculationsPrediction;
import com.example.laba.calculations.PredictionParameters;
import com.example.laba.cache.Cache;
import com.example.laba.exceptions.ServiceException;
import com.example.laba.stat.StatProv;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@RestController
public class PredictionController {
    int tmp_number;
    public int getTmp_number() {
        return tmp_number;
    }
    public void setTmp_number(int tmp_number) {
        this.tmp_number = tmp_number;
    }
    private CountController count = new CountController();
    private CalculationsPrediction calculationsPrediction = new CalculationsPrediction();
    private static final Logger logger = LogManager.getLogger(PredictionController.class);
    private StatisticsController statisticsController = new StatisticsController();
    private StatProv statProv = new StatProv();

    @GetMapping("/prediction")
    public ResponseEntity<Object> prediction(@RequestParam(value = "number", defaultValue = "0") String numb) {
        int number = 0;
        String answer;
        count.incCount();
        if (numb.matches("[-+]?\\d+")){
            number = Integer.parseInt(numb);
            this.tmp_number = number;
            if(number < 0){
                statProv.increaseTotalRequests();
                statProv.increaseWrongRequests();
                throw new ServiceException("Wrong value. Enter positive number, not a negative one!");
            }
            if(number > 10){
                statProv.increaseTotalRequests();
                statProv.increaseWrongRequests();
                throw new ServiceException("Wrong value. Enter number less 10!");
            }
        } else {
            statProv.increaseTotalRequests();
            statProv.increaseWrongRequests();
            throw new ServiceException("Wrong value. Enter integer, not a string!");
        }

        var solution = new CalculationsPrediction(new PredictionParameters(number, 0));
        solution.calculateRoot();
        if (solution.getRoot() == 1)
            answer = "Вы угадали число!";
        else
            answer = "Вы не угадали число. Загаданное число было: " + solution.getNumb();

        statProv.increaseTotalRequests();
        statProv.addRoot(number);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @GetMapping("/cache")
    public ResponseEntity<String> printCache() {
        return new ResponseEntity<>(Cache.getStaticStringCache(), HttpStatus.OK);
    }


    @PostMapping(value = "/findMax", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findMaxValue(@RequestBody String[] array) {
        return new ResponseEntity<>(calculationsPrediction.findMaxValue(array), HttpStatus.OK);
    }
}