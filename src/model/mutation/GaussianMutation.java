package model.mutation;

import model.Individual;
import java.util.Random;
import model.Genome;

import java.util.List;

public class GaussianMutation extends EmptyMutation implements MutationInterface {

    public GaussianMutation(double mutationRate) {
        super(mutationRate);
    }

    /**
     * Mutation function for mutating children with Gaussian.
     * Mutate all children with zero mean Gaussian
     * It might be possible to change the variance of the Gaussian
     * if we want to, but passing the desired variance requires a
     * different function header or that it is saved in the Individual
     * class or something like that.
     *
     * todo: mutationrate meenemen
     */
    @Override
    public List<Individual> doMutation(List<Individual> children) {
        int nr_children = children.size();
        int genome_size;
        Genome mutated_genome;
        double genome_array[];
        Random r = new Random();

        for (int i = 0; i < nr_children; i++) {
            mutated_genome = children.get(i).getGenome();
            genome_size = mutated_genome.getSize();
            genome_array = mutated_genome.getGenome();

            for (int j = 0; j < genome_size; j++) {
                genome_array[j] += r.nextGaussian(); // Can be done with mean and variance
                if (genome_array[j] > 5.0) {
                    genome_array[j] = 5.0;
                } else if (genome_array[j] < -5.0) {
                    genome_array[j] = -5.0;
                }
            }
            mutated_genome.setGenome(genome_array);
        }
        return children;
    }
}
