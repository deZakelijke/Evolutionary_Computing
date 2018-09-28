package model.termination;

import model.Population;

/**
 * runs indefinitly
 */
public class EmptyTermination implements TerminationContext {

    private int doneEvaluations =0;
    private int generations = 0;
    private boolean debug = false;

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

    @Override
    public void debugLine(String line) {
        if (debug) {
            System.err.println(line);
        }
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
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

    public boolean isDebug() {
        return debug;
    }

}
