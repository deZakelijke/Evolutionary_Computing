package model;


import model.mutation.Mutation;
import model.survival_selection.SurvivalSelection;
import model.recombination.Recombination;
import model.parent_selection.ParentSelection;
import model.stats.Statistic;
import model.termination.TerminationContext;
import org.vu.contest.ContestEvaluation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Population class,
 * holds a list of individuals and its stats
 * furthermore, important EA functions that work on the population are defined here
 */
public class Population {

    private List<Individual> populationList;
    private double lowestFitness;
    private double highestFitness;
    private double averageFitness;
    private int populationSize;

    //todo: add average, min en max age

    //todo: implementeren
    private double stdevFitness;

    //todo: naar stdev
    private double[] averageGenomes = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

    //todo: naar average of stdev
    private double averageOfAverageGenomes;

    /**
     * init / constructor
     *
     * @param sizePopulation
     * @param sizeGenomes
     * @param evaluator
     * @param terminator
     */
    public Population(int sizePopulation, int sizeGenomes, ContestEvaluation evaluator, TerminationContext terminator) {

        // build new
        setPopulationList(createNewPopulation(sizePopulation, sizeGenomes));

        // save size
        setPopulationSize(sizePopulation);

        // evaluate first, EA Fase: evaluate initial population
        for (Individual initialIndividual : populationList) {
            initialIndividual.setFitness((double) evaluator.evaluate(initialIndividual.getGenoType().getGenome()));
            terminator.addEvaluation();
        }
        reCalculateStats();
    }

    /**
     * creates new population of random individuals
     * EA fase: initialisation
     *
     * @param sizePopulation
     * @param sizeGenomes
     * @return
     */
    private List<Individual> createNewPopulation(int sizePopulation, int sizeGenomes) {
        List<Individual> newPopulation = new ArrayList<>(sizePopulation);
        for (int i = 0; i < sizePopulation; i++) {
            newPopulation.add(new Individual(new GenoType(sizeGenomes)));
        }
        return newPopulation;
    }

    /**
     * runs one generation of EA for the population
     *
     * @param evaluator fitness evaluation
     * @param mutation implementation
     * @param recombination implementation
     * @param survivalSelection (selecting for next generation) implementation
     * @param parentSelection (selecting parents) implementation
     * @param terminatorContext = context object which holds termination condition and nr of evaluations
     */
    public void runGeneration(ContestEvaluation evaluator, Mutation mutation, Recombination recombination, SurvivalSelection survivalSelection, ParentSelection parentSelection, TerminationContext terminatorContext) {

        try {

            // everyone is a (year?) older
            System.out.println("Increasing age of everyone");
            for (Individual individual : populationList) {
                individual.ageOneYear();
            }

            // selection for reproduction
            System.out.println("Selecting parents");
            List<Individual[]> parents = parentSelection.select(populationList);
            System.out.println(String.format("parents selected: %d", parents.size()));

            // recombination
            System.out.println("Starting recombinations");
            List<Individual> children = recombination.reproduce(parents);
            System.out.println(String.format("Children generated: %d", children.size()));

            // mutation
            System.out.println("Mutating children");
            mutation.doMutation(children);

            // reevaluate
            System.out.println("Evaluating children");
            for (Individual child : children) {
                child.setFitness((double) evaluator.evaluate(child.getGenoType().getGenome()));
                terminatorContext.addEvaluation();
            }

            // add newbournes to population
            System.out.println("Adding children to population");
            for (Individual child : children) {
                getPopulationList().add(child);
            }

            // natural selection
            System.out.println("Killing part of the population");
            survivalSelection.kill(populationList);

            // recalculate populationfitnessness
            System.out.println("Record stats");
            reCalculateStats();

            System.out.println(highestFitness);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     * calculate statistics of current population.
     * todo: afmaken
     */
    private void reCalculateStats() {

        populationList.sort(Comparator.comparing(Individual::getFitness));

//        String een = "";
//
//        for (Individual individual : populationList) {
//            een = een + "(" + String.valueOf(individual.getFitness()).substring(0, 4)  + ", " + String.valueOf(individual.getAge()) + "),\t";
//        }
//
//        System.out.println(een);

        double max = -60000000000000.0;
        double min = 600000000000000.0;
        double total = 0.0;
        double[] genomeSum = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

        for (Individual individual : populationList) {
            double fitness = individual.getFitness();
            double[] genome = individual.getGenoType().getGenome();
            if (fitness > max) {
                max = fitness;
            }
            if (fitness < min) {
                min = fitness;
            }
            total += fitness;
            int i = 0;
            for (double gen : genome) {
                genomeSum[i] += gen;
                i++;
            }
        }

        double sumStDevs = 0;
        int i = 0;
        for (double v : genomeSum) {
            averageGenomes[i] = v/((double) populationSize);
            sumStDevs += v/((double) populationSize);
            i++;
        }
        averageOfAverageGenomes = sumStDevs/10.0;

        averageFitness = total/ ((double) populationSize);

        highestFitness = max;
        lowestFitness = min;

        //todo: afmaken
        stdevFitness = 404.0;

    }

    public List<Individual> getPopulationList() {
        return populationList;
    }

    public void setPopulationList(List<Individual> populationList) {
        this.populationList = populationList;
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

    public double[] getAverageGenomes() {
        return averageGenomes;
    }

    public void setAverageGenomes(double[] averageGenomes) {
        this.averageGenomes = averageGenomes;
    }

    public double getAverageOfAverageGenomes() {
        return averageOfAverageGenomes;
    }

    public void setAverageOfAverageGenomes(double averageOfAverageGenomes) {
        this.averageOfAverageGenomes = averageOfAverageGenomes;
    }

    /** builds statistic object of current
     *
     * @return
     */
    public Statistic getStatistic() {

        //update
        reCalculateStats();

        // return build
        return new Statistic(lowestFitness, highestFitness, averageFitness, populationSize, stdevFitness, averageGenomes, averageOfAverageGenomes);

    }
}