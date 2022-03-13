package com.example.laba;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.laba.controller.PredictionController;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LabaApplicationTests {
    private final PredictionController predictionController = new PredictionController();
    @Test
    void testPredictionNumbers(){
        predictionController.guessing("4");
        String result = predictionController.getPrediction();
        String expected = "Вы угадали число: 4";
        assertEquals(expected, result);
    }

}
