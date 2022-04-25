package com.example.laba.cache;

import com.example.laba.calculations.PredictionParameters;
import com.example.laba.logger.ProgramLogger;

import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static final Map<PredictionParameters, Integer> solutions = new HashMap<>();

    public void add(@NotNull PredictionParameters params, @NotNull Integer root) {
        if (!solutions.containsKey(params)) {
            solutions.put(params, root);
            ProgramLogger.log(Level.INFO, "Value " + params + "@" + root + " added to cache!");
        }
    }

    public @Nullable Integer find(@NotNull PredictionParameters params) {
        if (solutions.containsKey(params))
            return solutions.get(params);

        ProgramLogger.log(Level.WARN, "Value " + params + " was not found in cache!");
        return null;
    }

    @Contract(pure = true)
    public @NotNull String getStringCache() {
        return ("Cache:\n" + solutions);
    }

    @Contract(pure = true)
    public static @NotNull String getStaticStringCache() {
        return ("Cache:\n" + solutions);
    }

    @Contract(pure = true)
    public Map<PredictionParameters, Integer> getSolutions() {
        return solutions;
    }
}