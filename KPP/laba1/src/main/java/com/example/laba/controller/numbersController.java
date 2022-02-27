package com.example.laba.controller;

import com.example.laba.entities.numbers;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class numbersController {
    @GetMapping("/guessing")
    numbers guessing(@RequestParam(value = "number", defaultValue = "0") String number) {
        int tmp;
        int max = 100;
        Random random = new Random();
        tmp = random.nextInt(max);
        int numb = Integer.parseInt(number);
        if (numb < tmp)
            return new numbers("Ваше число меньше заданного", tmp);
        else if (numb > tmp)
            return new numbers("Ваше число больше заданного", tmp);
        else
            return new numbers("Вы угадали", tmp);
    }
}