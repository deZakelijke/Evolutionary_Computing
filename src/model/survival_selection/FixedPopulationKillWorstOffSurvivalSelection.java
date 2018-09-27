package model.survival_selection;

import model.Individual;

import java.util.Comparator;
import java.util.List;

/**
 * kills the x worst performing of a population, until a fixed populationsize
 */
public class FixedPopulationKillWorstOffSurvivalSelection extends EmptySurvivalSelection implements SurvivalSelection {

    public FixedPopulationKillWorstOffSurvivalSelection(int initialPopulationSize) {
        super(initialPopulationSize);
    }

    @Override
    public void kill(List<Individual> populationList) {
        int i = 0;

        populationList.sort(Comparator.comparing(Individual::getFitness));

        while (populationList.size() > getPopulationsize()) {
            populationList.remove(0);
            i++;
        }

    }
}
