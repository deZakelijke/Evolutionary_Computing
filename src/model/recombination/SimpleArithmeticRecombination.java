package model.recombination;

import model.GenoType;
import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleArithmeticRecombination implements Recombination {
    Random rand = new Random();

    @Override
    public List<Individual> reproduce(List<Individual[]> selectedForReproduction) {
        int genomeSize = selectedForReproduction.get(0)[0].getGenoType().getSize();
        int parentCount = selectedForReproduction.get(0).length;

        List<Individual> children = new ArrayList<>();

        for (Individual[] parents : selectedForReproduction) {
            int crossoverPoint = rand.nextInt(genomeSize - 1) + 1;
            double[] arithmeticAverage = new double[genomeSize - crossoverPoint];

            for (Individual parent : parents) {
                int i = 0;
                double[] parentGenome = parent.getGenoType().getGenome();

                for (int j = crossoverPoint; j < genomeSize; j++) {
                    arithmeticAverage[i++] += parentGenome[j];
                }
            }

            for (int i = 0; j < arithmeticAverage.length; i++) {
                arithmeticAverage[i] /= parentCount;
            }

            for (int i = 0; i < parentCount; i++) {
                double[] childGenome = new double[genomeSize];
                double[] parentGenome = parents[i].getGenoType().getGenome();

                for (int j = 0; j < crossoverPoint; j++) {
                    childGenome[j] = parentGenome[j];
                }
                int j = 0;
                for (int k = crossoverPoint; k < genomeSize; k++) {
                    childGenome[k] = arithmeticAverage[j++];
                }

                Individual child = new Individual(new GenoType(childGenome));
                children.add(child);
            }
        }

        return children;
    }
}
