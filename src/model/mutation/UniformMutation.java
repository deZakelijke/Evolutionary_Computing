package model.mutation;

import model.Individual;
import model.GenoType;

import java.util.Random;
import java.util.List;


/***
 * =broken
 */
public class UniformMutation extends EmptyMutation implements Mutation {

    public UniformMutation(double mutationRate) {
        super(mutationRate);
    }

    /**
     * Mutation function for mutating children with uniform distribution.
     * todo: fixen en mutationrate meenemen
     */
    @Override
    public void doMutation(List<Individual> children) {
        int nr_children = children.size();
        int genome_size;
        GenoType mutated_genome;
        double genome_array[];
        double min = -5.0;
        double max = 5.0;
        Random r = new Random();

        for (int i = 0; i < nr_children; i++) {
            mutated_genome = children.get(i).getGenoType();
            genome_size = mutated_genome.getSize();
            genome_array = new double[genome_size];
            for (int j = 0; j < genome_size; j++) {
                genome_array[j] = r.nextDouble() * (max - min) + max;
                if (genome_array[j] > 5.0) {
                    genome_array[j] = 5.0;
                } else if (genome_array[j] < -5.0) {
                    genome_array[j] = -5.0;
                }

            }
            mutated_genome.setGenome(genome_array);
        }

    }
}
