package model.recombination;


import model.Individual;

import java.util.List;

/**
 * model interface for recombination object implementations
 *
 * every recombination object should have at least one function reproduce that defines how parents are combined into new individuals
 */
public interface Recombination {

    /**
     * takes in parents and outputs children
     *
     * @param selectedForReproduction
     * @return children
     */
    List<Individual> reproduce(List<Individual[]> selectedForReproduction);
}