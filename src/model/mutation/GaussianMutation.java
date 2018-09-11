package model.mutation;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.lang.object.Gaussian;

import java.util.List;

public class BasicMutation implements MutationInterface {

    /**
     * Mutation function for mutating children with Gaussian.
     * Mutate all children with zero mean Gaussian
     * It might be possible to change the variance of the Gaussian
     * if we want to, but passing the desired variance requires a
     * different function header or that it is saved in the Individual
     * class or something like that.
     */
    @Override
    public List<Individual> doMutation(List<Individual> children) {
        int nr_children = children.size();
        int genome_sze;
        Genome mutated_genome;
        double genome_array[];

        for (int i = 0; i < nr_children; i++) {
            mutated_genome = children.get(i).getGenome();
            genome_size = mutated_genome.getSize();
            genome_array = mutated_genome.getGenome();

            for (int j = 0; j < genome_size; j++) {
                genome_array[j] += Gaussian(); // Can be done with mean and variance
            }
        }
    }
}