package model.mutation;


import model.Individual;

import java.util.List;

/**
 * model interface for mutation object implementations
 *
 * every mutation object should have at least one function doMutation that defines how children are mutated
 */
public interface Mutation {

    /**
     * edits genomes of kids and outputs same kids
     *
     * @param children
     * @return
     */
    List<Individual> doMutation(List<Individual> children);
}