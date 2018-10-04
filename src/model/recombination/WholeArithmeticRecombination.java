package model.recombination;

import model.GenoType;
import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WholeArithmeticRecombination implements Recombination {
    Random rand = new Random();

    @Override
    public List<Individual> reproduce(List<Individual[]> selectedForReproduction) {
        int genomeSize = selectedForReproduction.get(0)[0].getGenoType().getSize();
        int parentCount = selectedForReproduction.get(0).length;

        List<Individual> children = new ArrayList<>();

        for (Individual[] parents : selectedForReproduction) {
            double[] alphas = new double[parentCount];
            double alphaSum = 0.0;
            double[][] parentGenomes = new double[parentCount][genomeSize];

            for (int i = 0; i < parentCount; i++) {
                double alpha = rand.nextDouble();
                alphas[i] = alpha;
                alphaSum += alpha;
                parentGenomes[i] = parents[i].getGenoType().getGenome();
            }

            for (int i = 0; i < parentCount; i++) {
                alphas[i] /= alphaSum;
            }

            for (int i = 0; i < parentCount; i++) {
                double[] childGenome = new double[genomeSize];

                for (int j = 0; j < genomeSize; j++) {
                    childGenome[j] += alphas[i] * parentGenomes[i][j];
                }

                Individual child = new Individual(new GenoType(childGenome));
                children.add(child);
            }

        }

        return children;
    }
}
