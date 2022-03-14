package com.example.laba.controller;
import java.util.Random;

import com.example.laba.exceptions.ServiceException;
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
    public String prediction(@RequestParam(value = "number", defaultValue = "0") String numb) {
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

        int random_number;
        int max = 10;
        Random random = new Random();
        random_number = random.nextInt(max);
        if (number < random_number)
            answer = "Ваше число меньше чем: " + random_number;
        else if (number > random_number)
            answer = "Ваше число больше чем: " + random_number;
        else
            answer = "Вы угадали число!";
        return answer;
    }
}