package model.stats;

import model.GenoType;
import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * utill class to collect all relevant statistics from population
 */
public class StatisticUtil {


    public static final String MAX = "max";
    public static final String MIN = "min";
    public static final String AVERAGE = "average";
    public static final String STDEV = "stdev";

    /**
     * diversity stats registration
     * @param populationList
     * @return
     */
    public static Map<String, Object> getGenomeStats(List<Individual> populationList) {

        Map<String, Object> result = new HashMap<>();

        List<double[]> genomes = populationList.stream().map(Individual::getGenoType).map(GenoType::getGenome).collect(Collectors.toList());
        double[] stdevArray = new double[genomes.get(0).length];
        for (int i = 0; i < stdevArray.length; i++) {
            List<Number> templist = new ArrayList<>();
            for (double[] genome : genomes) {
                templist.add(genome[i]);
            }
            stdevArray[i] = findStDev(templist);
        }

        List<Number> stdevList = new ArrayList<>();
        for (double v : stdevArray) {
            stdevList.add(v);
        }


        result.put(STDEV, stdevArray);
        result.put(AVERAGE+STDEV, findAverage(stdevList));

        return result;
    }

    /**
     * age stats registration
     * @param populationList
     * @return
     */
    public static Map<String, Number> getAgeStats(List<Individual> populationList) {

        Map<String, Number> result = new HashMap<>();
        List<Number> ages = populationList.stream().map(Individual::getAge).collect(Collectors.toList());

        int max = (Integer) findMax(ages);
        int min = (Integer) findMin(ages);
        double av = findAverage(ages);
        double stdev =  findStDev(ages);

        result.put(MAX, max);
        result.put(MIN, min);
        result.put(AVERAGE, av);
        result.put(STDEV, stdev);

        return result;

    }

    /**
     * fitness stats registration
     * @param populationList
     * @return
     */
    public static Map<String, Double> getFitnessStats(List<Individual> populationList) {

        Map<String, Double> result = new HashMap<>();
        List<Number> fitnesses = populationList.stream().map(Individual::getFitness).collect(Collectors.toList());

        double max = (Double) findMax(fitnesses);
        double min = (Double) findMin(fitnesses);
        double av = findAverage(fitnesses);
        double stdev = findStDev(fitnesses);

        result.put(MAX, max);
        result.put(MIN, min);
        result.put(AVERAGE, av);
        result.put(STDEV, stdev);

        return result;

    }

    /**
     * max of list of any number type
     * @param input
     * @return
     */
    private static Number findMax(List<Number> input) {

        Number max = input.get(0);

        for (Number number : input) {
            if (number.doubleValue() > max.doubleValue()) {
                max = number;
            }
        }
        return max;
    }

    /**
     * min of list of any number type
     * @param input
     * @return
     */
    private static Number findMin(List<Number> input) {
        Number min = input.get(0);

        for (Number number : input) {
            if ( number.doubleValue() <  min.doubleValue()) {
                min = number;
            }
        }
        return min;
    }

    /**
     * avergae of any number type
     * @param input
     * @return
     */
    private static double findAverage(List<Number> input) {

        double sum = 0.0;

        for (Number number : input) {
            sum +=  number.doubleValue();
        }

        return (sum/input.size());
    }

    /**
     * standard deviation of any number type
     * @param input
     * @return
     */
    private static double findStDev(List<Number> input) {
        double average = findAverage(input);
        double sigma = 0;
        for (Number number : input) {
            double addition = Math.pow(number.doubleValue() - average,2);
            sigma += addition;
        }
        double var = sigma/input.size();
        return Math.sqrt(var);
    }

}
