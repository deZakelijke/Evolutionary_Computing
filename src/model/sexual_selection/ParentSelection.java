package model.sexual_selection;


import model.Individual;

import java.util.List;

/**
 * model interface for sexual selection object implementations
 *
 * every sexual selection object should have at least one function select that defines which parents of a population get to mate
 */
public interface ParentSelection {

    /**
     * returns a list of parents
     *
     * @param populationList
     * @return list of couples
     */
    List<Individual[]> select(List<Individual> populationList);
}