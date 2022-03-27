package com.example.laba.calculations;
import java.util.Random;
import com.example.laba.SpringConfig;
import com.example.laba.cache.Cache;
import com.example.laba.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Solution {

    private final Cache cache;

    private final Parameters parameters;

    private Integer root;

    public Solution(Parameters params) {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cache = context.getBean("cache", Cache.class);
        context.close();

        this.parameters = params;
    }
    private int random_number;

    public void calculateRoot() {
        //int random_number;
        int max = 10;
        Random random = new Random();
        random_number = random.nextInt(max);
        parameters.setRandomValue(random_number);
        // Trying to find root in cache
        var temp = cache.find(parameters);
        if (temp != null) {
            ProgramLogger.log(Level.WARN, "Value found in cache!");
            setRoot(temp);

            return;
        }

        // If not found


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

}