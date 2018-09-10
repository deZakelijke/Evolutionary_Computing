package model.recombination;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class BasicRecombination implements RecombinationInterface {
    @Override
    public List<Individual> reproduce(List<Individual> selectedForReproduction) {
        throw new NotImplementedException();
    }
}
