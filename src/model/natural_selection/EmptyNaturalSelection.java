package model.natural_selection;

import model.Individual;
import java.util.List;

/**
 * does nothing
  */
public class EmptyNaturalSelection implements NaturalSelectionInterface{
    @Override
    public void kill(List<Individual> populationList) {
        return;
    }
}
