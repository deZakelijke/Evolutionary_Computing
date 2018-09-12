package model.terminator;

import model.Population;

/**
 * context object that needs to keep track of the evaluations
 */
public interface Terminator {

    /**
     * determines whether EA is done.
     * To do so it can use whatever it can find within the nr of evaluations and the population
     *
     * @param population
     * @return
     */
    boolean isItDone(Population population);

    void addEvaluation();

    public int getDoneEvaluations();
}

