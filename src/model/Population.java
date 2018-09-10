package model;


import model.mutation.MutationInterface;
import model.natural_selection.NaturalSelectionInterface;
import model.recombination.RecombinationInterface;
import model.sexual_selection.SexualSelectionInterface;
import model.terminator.Terminator;

import java.util.ArrayList;
import java.util.List;

public class Population {

    private List<Individual> populationList;
    private int lowestFitness;
    private int highestFitness;
    private int averageFitness;
    private int populationSize;

    public Population(int sizePopulation, int sizeGenomes) {
        setPopulationList(createNewPopulation(sizePopulation, sizeGenomes));
        setPopulationSize(sizePopulation);
    }

    private List<Individual> createNewPopulation(int sizePopulation, int sizeGenomes) {
        List<Individual> newPopulation = new ArrayList<>(sizePopulation);
        for (int i = 0; i < sizePopulation; i++) {
            newPopulation.add(new Individual(new Genome(sizeGenomes)));
        }
        return newPopulation;
    }

    public void runGeneration(Object evaluator, MutationInterface mutation, RecombinationInterface recombination, NaturalSelectionInterface naturalSelection, SexualSelectionInterface sexualSelection, Terminator terminator) {

        //todo: dynamics evaluatorobject bepalen
    }

    public List<Individual> getPopulationList() {
        return populationList;
    }

    public void setPopulationList(List<Individual> populationList) {
        this.populationList = populationList;
    }

    public int getLowestFitness() {
        return lowestFitness;
    }

    public void setLowestFitness(int lowestFitness) {
        this.lowestFitness = lowestFitness;
    }

    public int getHighestFitness() {
        return highestFitness;
    }

    public void setHighestFitness(int highestFitness) {
        this.highestFitness = highestFitness;
    }

    public int getAverageFitness() {
        return averageFitness;
    }

    public void setAverageFitness(int averageFitness) {
        this.averageFitness = averageFitness;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
}