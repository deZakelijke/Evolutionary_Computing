package model.natural_selection;

import model.Individual;

import java.util.List;
import java.util.Random;

public class FixedPopulationRandomNaturalSelection implements NaturalSelectionInterface



{

    Random rand = new Random();
    int size  = 0;

    public FixedPopulationRandomNaturalSelection(int initialPopulationSize) {
        this.size = initialPopulationSize;
    }

    @Override
    public void kill(List<Individual> populationList) {

        int i = 0;

        while (populationList.size() < size) {
            populationList.remove(rand.nextInt(populationList.size() - 1));
            i++;
        }

        System.out.println(String.format("Killed %d individuals, populationsize back to %d", i, populationList.size()));
    }
}
