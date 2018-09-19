package model.recombination;

import model.Genome;
import model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OnePointRandomRecombination implements RecombinationInterface {

    Random rand = new Random();

    // todo schaalbaar maken
    @Override
    public List<Individual> reproduce(List<Individual> selectedForReproduction) {
        List<Individual> kiddos = new ArrayList<>();

        int size = selectedForReproduction.size() - 2;

        for (int i = 0; i < size; i+=2) {
            Individual dad = selectedForReproduction.get(i);
            Individual mom = selectedForReproduction.get(i + 1);

            Genome dadGenome = dad.getGenome();
            Genome momGenome = mom.getGenome();

            Genome kiddo_1 = new Genome(dadGenome.onePointCrossover(1+rand.nextInt(dadGenome.getSize()-2), momGenome));
            Genome kiddo_2 = new Genome(momGenome.onePointCrossover(1+rand.nextInt(momGenome.getSize()-2), dadGenome));

            kiddos.add(new Individual(kiddo_1));
            kiddos.add(new Individual(kiddo_2));
        }

        return kiddos;
    }
}
