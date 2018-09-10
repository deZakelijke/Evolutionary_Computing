package model.mutation;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class EmptyMutation implements MutationInterface {

    @Override
    public List<Individual> doMutation(List<Individual> children) {
        return children;
    }
}
