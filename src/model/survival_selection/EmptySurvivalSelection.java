package model.survival_selection;

import model.Individual;
import java.util.List;

/**
 * does not kill anything
  */
public class EmptySurvivalSelection implements SurvivalSelection {

    private int populationsize;

    public EmptySurvivalSelection(int populationsize) {
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
