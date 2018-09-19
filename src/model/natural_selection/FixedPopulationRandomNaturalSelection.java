package model.natural_selection;

import model.Individual;

import java.util.List;
import java.util.Random;

/**
 * kills some random individuals untill fixed population
 */
public class FixedPopulationRandomNaturalSelection extends EmptyNaturalSelection implements NaturalSelectionInterface {

    Random rand = new Random();

    public FixedPopulationRandomNaturalSelection(int initialPopulationSize) {
        super(initialPopulationSize);
    }

    @Override
    public void kill(List<Individual> populationList) {

        int i = 0;

        while (populationList.size() > getPopulationsize()) {
            populationList.remove(rand.nextInt(populationList.size() - 1));
            i++;
        }

        System.out.println(String.format("Killed %d individuals, populationsize back to %d", i, populationList.size()));
    }
}
