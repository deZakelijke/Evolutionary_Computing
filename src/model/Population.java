package model;


import model.mutation.MutationInterface;
import model.natural_selection.NaturalSelectionInterface;
import model.recombination.RecombinationInterface;
import model.sexual_selection.SexualSelectionInterface;
import model.terminator.Terminator;
import org.vu.contest.ContestEvaluation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public boolean runGeneration(ContestEvaluation evaluator, MutationInterface mutation, RecombinationInterface recombination, NaturalSelectionInterface naturalSelection, SexualSelectionInterface sexualSelection, Terminator terminator) {

        // selection for reproduction
        List<Individual> parents = sexualSelection.select(populationList);

        // recombination
        List<Individual> children = recombination.reproduce(parents);

        // mutation
        children = mutation.doMutation(children);

        // reevaluate
        for (Individual child : children) {
            child.setFitness((double) evaluator.evaluate(child));
        }

        // natural selection
        naturalSelection.kill(populationList);

        // add to population
        for (Individual child : children) {
            getPopulationList().add(child);
        }

        // recalculate populationfitnessness
        reCalculateStats();

        // determine if we are done
        return (terminator.isItDone());

    }

    private void reCalculateStats() {
        throw new NotImplementedException();
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