package model.recombination;

import model.GenoType;
import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CenterOfMassRecombination implements Recombination {
    Random rand = new Random();

    @Override
    public List<Individual> reproduce(List<Individual[]> selectedForReproduction) {
        int genomeSize = selectedForReproduction.get(0)[0].getGenoType().getSize();
        int parentCount = selectedForReproduction.get(0).length;
        double parentCountRecip = 2 / parentCount;

        List<Individual> children = new ArrayList<>();

        for (Individual[] parents : selectedForReproduction) {
            double[][] parentGenomes = new double[parentCount][genomeSize];
            double[] centerOfMass = new double[genomeSize];

            for (int i = 0; i < parentCount; i++) {
                double[] parentGenome = parents[i].getGenoType().getGenome();
                parentGenomes[i] = parentGenome;

                for (int j = 0; j < genomeSize; j++) {
                    centerOfMass[j] += parentCountRecip * parentGenome[j];
                }
            }

            /* Blx-alpha */
            for (int i = 0; i < parentCount; i++) {
                double[] childGenome = new double[genomeSize];

                for (int j = 0; j < genomeSize; j++) {
                    double virtualAllele = centerOfMass[j] - parentGenomes[i][j];
                    double lowerAllele = Math.min(parentGenomes[i][j], virtualAllele);
                    double higherAllele = Math.max(parentGenomes[i][j], virtualAllele);
                    double diff = 0.5 * (higherAllele - lowerAllele);

                    double lowerBound = lowerAllele - diff;
                    double higherBound = higherAllele + diff;

                    childGenome[j] = lowerBound + (higherBound - lowerBound) * rand.nextDouble();
                }
                Individual child = new Individual(new GenoType(childGenome));
                children.add(child);
            }

        }

        return children;
    }
}
