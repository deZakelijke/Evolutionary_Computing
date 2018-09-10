package model.sexual_selection;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class EmptySexualSelection implements SexualSelectionInterface {
    @Override
    public List<Individual> select(List<Individual> populationList) {
        List<Individual> empty = new ArrayList<>();
        return empty;
    }
}
