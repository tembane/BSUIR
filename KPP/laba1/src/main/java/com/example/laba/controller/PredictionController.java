package com.example.laba.controller;

import com.example.laba.calculations.Solution;
import com.example.laba.calculations.Parameters;
import com.example.laba.cache.Cache;
import com.example.laba.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PredictionController {

    int tmp_number;

    public int getTmp_number() {
        return tmp_number;
    }
    public void setTmp_number(int tmp_number) {
        this.tmp_number = tmp_number;
    }

    @GetMapping("/prediction")
    public ResponseEntity<Object> prediction(@RequestParam(value = "number", defaultValue = "0") String numb) {
        int number;
        String answer;

        if (numb.matches("[-+]?\\d+")){

            number = Integer.parseInt(numb);
            this.tmp_number = number;
            if(number < 0){
                throw new ServiceException("Wrong value. Enter positive number, not a negative one!");
            }
            if(number > 10){
                throw new ServiceException("Wrong value. Enter number less 10!");
            }
        } else {
            throw new ServiceException("Wrong value. Enter integer, not a string!");
        }

        var solution = new Solution(new Parameters(number, 0));
        solution.calculateRoot();
        if (solution.getRoot() == 1)
            answer = "Вы угадали число!";
        else
            answer = "Вы не угадали число. Загаданное число было: " + solution.getNumb();

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @GetMapping("/cache")
    public ResponseEntity<String> printCache() {
        return new ResponseEntity<>(Cache.getStaticStringCache(), HttpStatus.OK);
    }
}