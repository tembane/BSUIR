package com.example.laba.entities;

public class Prediction {
    String prediction;
    public Prediction() {this.prediction = "None"; }
    public Prediction(String pred) {
        this.prediction = pred;
    }
    public String getPrediction() {
        return prediction;
    }
    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
