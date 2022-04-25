package com.example.laba.calculations;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;
import java.util.Objects;

public class PredictionParameters {

    // Method equals() overriding
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        PredictionParameters params = (PredictionParameters) obj;

        return  Objects.equals(enteredValue,  params.enteredValue)  &&
                Objects.equals(randomValue, params.randomValue);
    }

    // Method hashCode() overriding
    @Override
    public int hashCode() {
        return Objects.hash(enteredValue, randomValue);
    }

    private @Nullable Integer enteredValue;

    private @Nullable Integer randomValue;


    public PredictionParameters(
            @Nullable Integer firstValue,
            @Nullable Integer secondValue
    ) {
        if (firstValue == null && secondValue == null)
            throw new IllegalArgumentException("No first and second values!");

        if (firstValue == null)
            throw new IllegalArgumentException("No first value!");

        if (secondValue == null)
            throw new IllegalArgumentException("No second value!");

        this.enteredValue = firstValue;
        this.randomValue = secondValue;
    }

    @NotNull
    @Contract(pure = true)
    public Integer getEnteredValue() {
        assert enteredValue != null;
        return enteredValue;
    }

    @NotNull
    @Contract(pure = true)
    public Integer getRandomValue() {
        assert randomValue != null;
        return randomValue;
    }


    public void setEnteredValue(@Nullable Integer enteredValue) {
        this.enteredValue = enteredValue;
    }

    public void setRandomValue(@Nullable Integer randomValue) {
        this.randomValue = randomValue;
    }
}
