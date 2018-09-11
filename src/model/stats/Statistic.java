package model.stats;

import java.util.Arrays;

public class Statistic {

    private double lowestFitness;
    private double highestFitness;
    private double averageFitness;
    private int populationSize;
    private double stdevFitness;
    private double[] stdevGenomes;
    private double averageOfStDevGenomes;

    public Statistic(double lowestFitness, double highestFitness, double averageFitness, int populationSize, double stdevFitness, double[] stdevGenomes, double averageOfStDevGenomes) {
        this.lowestFitness = lowestFitness;
        this.highestFitness = highestFitness;
        this.averageFitness = averageFitness;
        this.populationSize = populationSize;
        this.stdevFitness = stdevFitness;
        this.stdevGenomes = stdevGenomes;
        this.averageOfStDevGenomes = averageOfStDevGenomes;
    }

    public double getLowestFitness() {
        return lowestFitness;
    }

    public void setLowestFitness(double lowestFitness) {
        this.lowestFitness = lowestFitness;
    }

    public double getHighestFitness() {
        return highestFitness;
    }

    public void setHighestFitness(double highestFitness) {
        this.highestFitness = highestFitness;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public void setAverageFitness(double averageFitness) {
        this.averageFitness = averageFitness;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public double getStdevFitness() {
        return stdevFitness;
    }

    public void setStdevFitness(double stdevFitness) {
        this.stdevFitness = stdevFitness;
    }

    public double[] getStdevGenomes() {
        return stdevGenomes;
    }

    public void setStdevGenomes(double[] stdevGenomes) {
        this.stdevGenomes = stdevGenomes;
    }

    public double getAverageOfStDevGenomes() {
        return averageOfStDevGenomes;
    }

    public void setAverageOfStDevGenomes(double averageOfStDevGenomes) {
        this.averageOfStDevGenomes = averageOfStDevGenomes;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "lowestFitness=" + lowestFitness +
                ", highestFitness=" + highestFitness +
                ", averageFitness=" + averageFitness +
                ", populationSize=" + populationSize +
                ", stdevFitness=" + stdevFitness +
                ", stdevGenomes=" + Arrays.toString(stdevGenomes) +
                ", averageOfStDevGenomes=" + averageOfStDevGenomes +
                '}';
    }
}
