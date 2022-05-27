package com.example.laba.stat;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Statistics {
    public Statistics(){
        totalReq = 0;
        totalWrongReq = 0;
        minValue = 0;
        maxValue = 0;
        mostCommonValue = 0;
    }
    public Integer totalReq;
    public Integer totalWrongReq;
    public Integer minValue;
    public Integer maxValue;
    public Integer mostCommonValue;
}
