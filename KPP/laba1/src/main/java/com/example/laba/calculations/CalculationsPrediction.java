package com.example.laba.calculations;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


import com.example.laba.stat.StatProv;
import com.example.laba.SpringConfig;
import com.example.laba.cache.Cache;
import com.example.laba.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalculationsPrediction {

    private final StatProv statProv = new StatProv();
    private final Cache cache;

    private PredictionParameters parameters;

    private Integer root;

    private int random_number;

    public CalculationsPrediction(PredictionParameters params) {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cache = context.getBean("cache", Cache.class);
        context.close();

        this.parameters = params;
    }

    public void calculateRoot() {
        int max = 10;
        Random random = new Random();
        random_number = random.nextInt(max);
        parameters.setRandomValue(random_number);
        // Trying to find root in cache
        var temp = cache.find(parameters);
        if (temp != null) {
            ProgramLogger.log(Level.WARN, "Value found in cache!");

            return;
        }
        if (parameters.getEnteredValue() == parameters.getRandomValue()){
            temp = 1;
        } else{
            temp = 0;
        }

        setRoot(temp);
        cache.add(parameters, root);
    }

    public Integer getNumb(){
        return random_number;
    }
    public Integer getRoot() {
        return root;
    }
    public void setRoot(@Nullable Integer root) {
        this.root = root;
    }
    public OptionalInt findMaxValue(String[] arr){
        IntStream steam = Stream.of(arr).mapToInt(Integer::parseInt);
        return steam.filter(x -> x>0).max();
    }

    public CalculationsPrediction() {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cache = context.getBean("cache", Cache.class);
        context.close();
    }
}