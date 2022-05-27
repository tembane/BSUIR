package com.example.laba.controller;


import com.example.laba.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountController {
    static int count = 0;

    synchronized public void incCount(){
        count++;
        ProgramLogger.log(Level.INFO,"Counter increment!");
    }
    synchronized public int getValue(){
        return count;
    }
    @GetMapping(value = "/counter")
    synchronized public ResponseEntity counter (){
        return ResponseEntity.ok().body(count);
    }
    public int getCount(){
        return count;
    }
}

//responseEntity;