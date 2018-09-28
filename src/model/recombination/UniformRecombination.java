package model.recombination;

import model.GenoType;
import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniformRecombination implements Recombination {
    // public static final int CHILD_COUNT = 2;

    Random rand = new Random();

    @Override
    public List<Individual> reproduce(List<Individual[]> selectedForReproduction) {
        int genomeSize = selectedForReproduction.get(0)[0].getGenoType().getSize();
        int parentCount = selectedForReproduction.get(0).length;

        List<Individual> children = new ArrayList<>();

        for (Individual[] parents : selectedForReproduction) {
            for (int i = 0; i < parentCount; i++) {
                double[] childGenome = new double[genomeSize];

                for (int j = 0; j < genomeSize; j++) {
                    Individual parent = parents[rand.nextInt(parentCount)];
                    double allele = parent.getGenoType().getAllele(j);
                    childGenome[j] = allele;
                }

                Individual child = new Individual(new GenoType(childGenome));
                children.add(child);
            }
        }

        return children;
    }
}
