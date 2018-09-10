package model.natural_selection;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class BasicNaturalSelection implements NaturalSelectionInterface{
    @Override
    public void kill(List<Individual> populationList) {
        throw new NotImplementedException();
    }
}
