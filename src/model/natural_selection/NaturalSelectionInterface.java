package model.natural_selection;


import model.Individual;

import java.util.List;

/**
 * model interface for natural selection object implementations
 *
 * every natural selection object should have at least one function kill that defines how the population is trimmed down
 */
public interface NaturalSelectionInterface {

    /**
     * removes a number of individuals from population
     *
     * @param populationList
     */
    void kill(List<Individual> populationList);
}