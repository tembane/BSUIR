package com.example.laba.controller;


import com.example.laba.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Counter {
    static int count = 0;

    synchronized public void incrCount(){
        count++;
        ProgramLogger.log(Level.INFO, "Counter ++, New counter value is "+count+".");
    }

    @GetMapping("/count")
    synchronized public String countInfo(){
        return "Count equal to " + count;
    }

}
