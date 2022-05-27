package com.example.laba;
import com.example.laba.controller.CountController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.laba.controller.PredictionController;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class LabaApplicationTests {

    private final PredictionController predictionController = new PredictionController();

    @Test
    void testPredictionNumbers(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountController countController = new CountController();
        IntStream.range(0, 10000).forEach(count ->executorService.execute(countController::incCount));

        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e){
            executorService.shutdownNow();
        }
        assertEquals(10000, countController.getValue(), "Synchronization check");
    }
}
