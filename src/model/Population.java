package model;


import model.exception.EvaluationsExhaustedException;
import model.mutation.Mutation;
import model.stats.StatisticUtil;
import model.survival_selection.SurvivalSelection;
import model.recombination.Recombination;
import model.parent_selection.ParentSelection;
import model.stats.Statistic;
import model.termination.TerminationContext;
import org.vu.contest.ContestEvaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Population class,
 * holds a list of individuals and its stats
 * furthermore, important EA functions that work on the population are defined here
 */
public class Population {

    private List<Individual> populationList;
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

    private double[] stdevGenomes = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    private double averageOfstdevGenomes;

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
            try {
                if (terminator.isItDone(this)) {
                    throw new EvaluationsExhaustedException(terminator.getDoneEvaluations(), evaluator);
                }
                try {
                    initialIndividual.setFitness((double) evaluator.evaluate(initialIndividual.getGenoType().getGenome()));
                } catch (NullPointerException passOn) {
                    throw new EvaluationsExhaustedException(passOn);
                }
                terminator.addEvaluation();
            } catch (EvaluationsExhaustedException e) {
                e.printStackTrace();
                terminator.debugLine(e.toString());
                terminator.debugLine("Warning, stopped creation population, check evaluation object!! you might have used an old one that is already over its limit");
                killRun(1);
                break;
            }
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

    private void killRun(int code) {
        System.exit(code);
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
            terminatorContext.debugLine("Increasing age of everyone");
            for (Individual individual : populationList) {
                individual.ageOneYear();
            }

            // selection for reproduction
            terminatorContext.debugLine("Selecting parents");
            List<Individual[]> parents = parentSelection.select(populationList);
            terminatorContext.debugLine(String.format("parents selected: %d", parents.size()));

            // recombination
            terminatorContext.debugLine("Starting recombinations");
            List<Individual> children = recombination.reproduce(parents);
            terminatorContext.debugLine(String.format("Children generated: %d", children.size()));

            // mutation
            terminatorContext.debugLine("Mutating children");
            mutation.doMutation(children);

            // reevaluate
            terminatorContext.debugLine("Evaluating children");
            for (Individual child : children) {
                try {
                    if (terminatorContext.isItDone(this)) {
                        throw new EvaluationsExhaustedException(terminatorContext.getDoneEvaluations(), evaluator);
                    }
                    try {
                        child.setFitness((double) evaluator.evaluate(child.getGenoType().getGenome()));
                    } catch (NullPointerException passOn) {
                        throw new EvaluationsExhaustedException(passOn);
                    }
                    terminatorContext.addEvaluation();
                } catch (EvaluationsExhaustedException e) {
                    e.printStackTrace();
                    terminatorContext.debugLine(e.toString());
                    terminatorContext.debugLine("Warning, stopped evaluating children, probably because you ran out of evaluations");
                    break;
                }
            }

            // add newbournes to population
            terminatorContext.debugLine("Adding children to population");
            for (Individual child : children) {
                getPopulationList().add(child);
            }

            // natural selection
            terminatorContext.debugLine("Killing part of the population");
            survivalSelection.kill(populationList);

            // done

        } catch (Exception e) {
            e.printStackTrace();
            terminatorContext.debugLine(e.getMessage());
            throw e;
        }
    }

    /**
     * calculate statistics of current population.
     *
     */
    private void reCalculateStats() {

        try {

            Map ageResults = StatisticUtil.getAgeStats(populationList);
            maxAge = (Integer) ageResults.get(StatisticUtil.MAX);
            minAge = (Integer) ageResults.get(StatisticUtil.MIN);
            averageAge = (Double) ageResults.get(StatisticUtil.AVERAGE);
            stdevAge = (Double) ageResults.get(StatisticUtil.STDEV);

            Map fitnessResults = StatisticUtil.getFitnessStats(populationList);
            maxFitness = (Double) fitnessResults.get(StatisticUtil.MAX);
            minFitness = (Double) fitnessResults.get(StatisticUtil.MIN);
            averageFitness = (Double) fitnessResults.get(StatisticUtil.AVERAGE);
            stdevFitness = (Double) fitnessResults.get(StatisticUtil.STDEV);

            Map genomeResults = StatisticUtil.getGenomeStats(populationList);
            stdevGenomes = (double[]) genomeResults.get(StatisticUtil.STDEV);
            averageOfstdevGenomes = (Double) genomeResults.get(StatisticUtil.AVERAGE + StatisticUtil.STDEV);

        } catch (Exception e) {
            System.out.println("Error calculating statistics:");
            e.printStackTrace();
            System.out.println("Continue anyway");
        }

    }

    public List<Individual> getPopulationList() {
        return populationList;
    }

    public void setPopulationList(List<Individual> populationList) {
        this.populationList = populationList;
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

    /** builds statistic object of current population
     *
     * @return
     */
    public Statistic getStatistic() {

        // recalculate populationfitnessness
        reCalculateStats();

        // return builded statistic
        return new Statistic(populationSize, minFitness, maxFitness, averageFitness, stdevFitness, maxAge, minAge, averageAge, stdevAge, stdevGenomes, averageOfstdevGenomes);

    }
}