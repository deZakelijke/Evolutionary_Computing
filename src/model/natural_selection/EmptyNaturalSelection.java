package model.natural_selection;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class EmptyNaturalSelection implements NaturalSelectionInterface{
    @Override
    public void kill(List<Individual> populationList) {
        return;
    }
}