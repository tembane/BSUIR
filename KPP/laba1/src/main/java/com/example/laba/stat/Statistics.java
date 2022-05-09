package com.example.laba.stat;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Statistics {
    protected Integer totalReq = 0;
    protected Integer totalWrongReq = 0;

    protected Integer minValue = 0;
    protected Integer maxValue = 0;
    protected Integer mostCommonValue = 0;

}
