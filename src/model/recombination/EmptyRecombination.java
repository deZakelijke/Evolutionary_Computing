package model.recombination;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class EmptyRecombination implements RecombinationInterface {
    @Override
    public List<Individual> reproduce(List<Individual> selectedForReproduction) {
        List<Individual> empty = new ArrayList<>();
        return empty;
    }
}
