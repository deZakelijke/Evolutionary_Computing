package model.natural_selection;

import model.Individual;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * kills the x worst performing of a population, until a fixed populationsize
 */
public class FixedPopulationKillWorstOffNaturalSelection implements NaturalSelectionInterface



{

    int size  = 0;

    public FixedPopulationKillWorstOffNaturalSelection(int initialPopulationSize) {
        this.size = initialPopulationSize;
    }

    @Override
    public void kill(List<Individual> populationList) {

        int i = 0;

        populationList.sort(Comparator.comparing(Individual::getFitness));

        while (populationList.size() > size) {
            populationList.remove(0);
            i++;
        }

        System.out.println(String.format("Killed %d individuals, populationsize back to %d", i, populationList.size()));
    }
}
