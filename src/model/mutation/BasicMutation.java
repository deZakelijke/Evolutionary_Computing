package model.mutation;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class BasicMutation implements MutationInterface {

    /**
     * Mutation function for mutating children.
     * Two options: uniform and Gaussian
     */
    @Override
    public List<Individual> doMutation(List<Individual> children) {
        throw new NotImplementedException();
    }
}
