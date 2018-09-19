package model.stats;

import java.util.Arrays;

/**
 * data object to hold data of the population at one moment in time
 */
public class Statistic {

    private int populationSize;


    //statistical
    private double minFitness;
    private double maxFitness;
    private double averageFitness;
    private double stdevFitness;

    private int maxAge;
    private int minAge;
    private double averageAge;
    private double stdevAge;

    private double[] stdevGenomes;
    private double averageOfstdevGenomes;


    public Statistic(int populationSize, double minFitness, double maxFitness, double averageFitness, double stdevFitness, int maxAge, int minAge, double averageAge, double stdevAge, double[] stdevGenomes, double averageOfstdevGenomes) {
        this.populationSize = populationSize;
        this.minFitness = minFitness;
        this.maxFitness = maxFitness;
        this.averageFitness = averageFitness;
        this.stdevFitness = stdevFitness;
        this.maxAge = maxAge;
        this.minAge = minAge;
        this.averageAge = averageAge;
        this.stdevAge = stdevAge;
        this.stdevGenomes = stdevGenomes;
        this.averageOfstdevGenomes = averageOfstdevGenomes;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "populationSize=" + populationSize +
                ", minFitness=" + minFitness +
                ", maxFitness=" + maxFitness +
                ", averageFitness=" + averageFitness +
                ", stdevFitness=" + stdevFitness +
                ", maxAge=" + maxAge +
                ", minAge=" + minAge +
                ", averageAge=" + averageAge +
                ", stdevAge=" + stdevAge +
                ", averageOfstdevGenomes=" + averageOfstdevGenomes +
                ", stdevGenomes=" + Arrays.toString(stdevGenomes) +
                '}';
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public double getMinFitness() {
        return minFitness;
    }

    public void setMinFitness(double minFitness) {
        this.minFitness = minFitness;
    }

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public void setAverageFitness(double averageFitness) {
        this.averageFitness = averageFitness;
    }

    public double getStdevFitness() {
        return stdevFitness;
    }

    public void setStdevFitness(double stdevFitness) {
        this.stdevFitness = stdevFitness;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public double getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(double averageAge) {
        this.averageAge = averageAge;
    }

    public double getStdevAge() {
        return stdevAge;
    }

    public void setStdevAge(double stdevAge) {
        this.stdevAge = stdevAge;
    }

    public double[] getStdevGenomes() {
        return stdevGenomes;
    }

    public void setStdevGenomes(double[] stdevGenomes) {
        this.stdevGenomes = stdevGenomes;
    }

    public double getAverageOfstdevGenomes() {
        return averageOfstdevGenomes;
    }

    public void setAverageOfstdevGenomes(double averageOfstdevGenomes) {
        this.averageOfstdevGenomes = averageOfstdevGenomes;
    }
}
