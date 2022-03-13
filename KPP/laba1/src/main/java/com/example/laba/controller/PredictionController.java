package com.example.laba.controller;

import com.example.laba.entities.Prediction;
import java.util.Random;

import com.example.laba.exceptions.ServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PredictionController {
    @GetMapping("/guessing")
    public Prediction guessing(@RequestParam(value = "Prediction", defaultValue = "0") String numb) {
        int number = 0;
        if (numb.matches("[-+]?\\d+")){
            number = Integer.parseInt(numb);

            if(number < 0 || number > 10){
                throw new ServiceException("Wrong value");
            }
        } else {
            throw new ServiceException("Wrong value");
        }

        Prediction prediction = new Prediction();
        int random_number;
        int max = 10;
        Random random = new Random();
        random_number = random.nextInt(max);
        if (number < random_number)
            prediction.setPrediction("Ваше число меньше чем: " + random_number);
        else if (number > random_number)
            prediction.setPrediction("Ваше число больше чем: " + random_number);
        else
            prediction.setPrediction("Вы угадали число: " + random_number);
        return prediction;
    }
    public String getPrediction(){
        return "Вы угадали число: 4";
    }
}