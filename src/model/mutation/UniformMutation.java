package model.mutation;

import model.Individual;
import model.Genome;

import java.util.Random;
import java.util.List;

public class UniformMutation implements MutationInterface {

    /**
     * Mutation function for mutating children with uniform distribution.
     *
     */
    @Override
    public List<Individual> doMutation(List<Individual> children) {
        int nr_children = children.size();
        int genome_size;
        Genome mutated_genome;
        double genome_array[];
        double min = -5.0;
        double max = 5.0;
        Random r = new Random();

        for (int i = 0; i < nr_children; i++) {
            mutated_genome = children.get(i).getGenome();
            genome_size = mutated_genome.getSize();
            genome_array = new double[genome_size];
            for (int j = 0; j < genome_size; j++) {
                genome_array[j] = r.nextDouble() * (max - min) + max;
            }
            mutated_genome.setGenome(genome_array);
        }

        return children;
    }
}
