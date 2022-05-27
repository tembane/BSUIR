package com.example.laba.stat;

import com.example.laba.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Service
public class StatProv{
    public static Statistics statistics = new Statistics();
    private static List<Integer> roots = new ArrayList<>();
    private static boolean shouldBeRecalculated = true;

    public Statistics getStats() {
        return statistics;
    }
    public void increaseTotalRequests() {
        statistics.totalReq++;
    }
    public void increaseWrongRequests() {
        statistics.totalWrongReq++;
    }

    public void calculate() {

        ProgramLogger.log(Level.INFO, "Collecting stats...");

        if (!shouldBeRecalculated) {
            ProgramLogger.log(Level.WARN, "Stats need not to be recollected!");
            return;
        }

        try {
            roots = roots
                    .stream()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            statistics.mostCommonValue = roots
                    .stream()
                    .reduce(BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(roots, o)))
                    ).orElse(0);

            statistics.minValue = roots
                    .stream()
                    .min(Comparator.comparing(Integer::valueOf))
                    .orElse(0);

            statistics.maxValue = roots
                    .stream()
                    .max(Comparator.comparing(Integer::valueOf))
                    .orElse(0);

            ProgramLogger.log(Level.WARN, "Stats recollected!");

            shouldBeRecalculated = false;
        } catch (NullPointerException exception) {
            ProgramLogger.log(Level.ERROR, "Error while collecting stats!");
            throw new RuntimeException(exception);
        }
    }

    public void addRoot(@NotNull Integer root) {
        roots.add(root);
        shouldBeRecalculated = true;
    }

}