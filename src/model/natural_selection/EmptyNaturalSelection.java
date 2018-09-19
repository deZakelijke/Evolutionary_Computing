package model.natural_selection;

import model.Individual;
import java.util.List;

/**
 * does nothing
  */
public class EmptyNaturalSelection implements NaturalSelectionInterface{

    private int populationsize;

    public EmptyNaturalSelection(int populationsize) {
        this.populationsize = populationsize;
    }

    @Override
    public void kill(List<Individual> populationList) {
        return;
    }

    public int getPopulationsize() {
        return populationsize;
    }

    public void setPopulationsize(int populationsize) {
        this.populationsize = populationsize;
    }
}
