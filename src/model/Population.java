package model;


import model.mutation.MutationInterface;
import model.natural_selection.NaturalSelectionInterface;
import model.recombination.RecombinationInterface;
import model.sexual_selection.SexualSelectionInterface;
import model.stats.Statistic;
import model.terminator.Terminator;
import org.vu.contest.ContestEvaluation;

import java.util.ArrayList;
import java.util.List;

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

    //todo: naar average van stdev
    private double averageOfAverageGenomes;

    public Population(int sizePopulation, int sizeGenomes, ContestEvaluation evaluator, Terminator terminator) {

        // build new
        setPopulationList(createNewPopulation(sizePopulation, sizeGenomes));

        // save size
        setPopulationSize(sizePopulation);

        // evaluate first
        for (Individual initialIndividual : populationList) {
            initialIndividual.setFitness((double) evaluator.evaluate(initialIndividual.getGenome().getGenome()));
            terminator.addEvaluation();
        }
        reCalculateStats();
    }

    private List<Individual> createNewPopulation(int sizePopulation, int sizeGenomes) {
        List<Individual> newPopulation = new ArrayList<>(sizePopulation);
        for (int i = 0; i < sizePopulation; i++) {
            newPopulation.add(new Individual(new Genome(sizeGenomes)));
        }
        return newPopulation;
    }

    public void runGeneration(ContestEvaluation evaluator, MutationInterface mutation, RecombinationInterface recombination, NaturalSelectionInterface naturalSelection, SexualSelectionInterface sexualSelection, Terminator terminator) {

        try {

            // selection for reproduction
            System.out.println("Selecting parents");
            List<Individual> parents = sexualSelection.select(populationList);
            System.out.println(String.format("parents selected: %d", parents.size()));

            // recombination
            System.out.println("Starting recombinations");
            List<Individual> children = recombination.reproduce(parents);
            System.out.println(String.format("Children generated: %d", children.size()));

            // mutation
            System.out.println("Mutating children");
            children = mutation.doMutation(children);

            // reevaluate
            System.out.println("Evaluating children");
            for (Individual child : children) {
                child.setFitness((double) evaluator.evaluate(child.getGenome().getGenome()));
                terminator.addEvaluation();
            }

            // add newbournes to population
            System.out.println("Adding children to population");
            for (Individual child : children) {
                getPopulationList().add(child);
            }

            // natural selection
            System.out.println("Killing part of the population");
            naturalSelection.kill(populationList);

            // everyone is a (year?) older
            System.out.println("Increasing age of everyone");
            for (Individual individual : populationList) {
                individual.ageOneYear();
            }

            // recalculate populationfitnessness
            System.out.println("Record stats");
            reCalculateStats();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private void reCalculateStats() {

        double max = -6.0;
        double min = 6.0;
        double total = 0.0;
        double[] genomeSum = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

        for (Individual individual : populationList) {
            double fitness = individual.getFitness();
            double[] genome = individual.getGenome().getGenome();
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

    public Statistic getStatistic() {

        return new Statistic(lowestFitness, highestFitness, averageFitness, populationSize, stdevFitness, averageGenomes, averageOfAverageGenomes);

    }
}