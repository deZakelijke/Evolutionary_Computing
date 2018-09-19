package model.terminator;

import model.Population;

/**
 * runs indefinitly
 */
public class EmptyTermination implements TerminationContext {

    private int doneEvaluations =0;
    private int generations = 0;

    public EmptyTermination() {
    }

    @Override
    public boolean isItDone(Population population) {
        return false;
    }

    @Override
    public void addEvaluation() {
        doneEvaluations++;
    }

    @Override
    public void addGeneration() {
        generations++;
    }

    @Override
    public int getDoneEvaluations() {
        return doneEvaluations;
    }

    @Override
    public int getGenerationNumber() {
        return generations;
    }

    public void setDoneEvaluations(int doneEvaluations) {
        this.doneEvaluations = doneEvaluations;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }
}
