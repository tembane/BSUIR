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
        predictionController.prediction("4");
        int result = predictionController.getTmp_number();
        int expected = 4;
        assertEquals(expected, result);
    }

}
