package model.parent_selection;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Random;

public class StochasticParentSelectionNoRepetition extends EmptyParentSelection implements ParentSelection {


    /**
     * init / constructor
     *
     * @param nr_parents
     * @param nr_couples
     */
    public StochasticParentSelectionNoRepetition(int nr_parents, int nr_couples) {
        super(nr_parents, nr_couples);
    }

    /**
     * Select parents stochastically, based on their fitness.
     * Parents will be selected for a couple with a probability equal to
     * their share of the toal fitness score.
     * Parents can in this version be selected multiple times.
     */
    @Override
    public List<Individual[]> select(List<Individual> populationList) {
        int length = populationList.size();
        if (length < this.nr_parents) return null;
        List<Individual[]> parents = new ArrayList<Individual[]>();
        Random r = new Random();
        int random_index;
        HashSet<Integer> selected = new HashSet<Integer>();

        double[] cumulative_fitness = new double[length];
        cumulative_fitness[0] = populationList.get(0).getFitness();
        for (int i = 1; i < length; i++) {
            cumulative_fitness[i] = cumulative_fitness[i - 1] + populationList.get(i).getFitness();
        }

        for (int i = 0; i < this.nr_couples; i++) {
            Individual[] new_couple = new Individual[this.nr_parents];
            while (selected.size() < this.nr_parents) {
                random_index = get_random_index(cumulative_fitness, r, length);
                selected.add(random_index);
            }

            int j = 0;
            for (int index : selected) {
                new_couple[j] = populationList.get(index);
                j++;
            }

            parents.add(new_couple);
        }
        
        return parents;
    }

    private int get_random_index(double[] cumulative_fitness, Random r, int length) {
        double random_number = r.nextDouble() * cumulative_fitness[length - 1];
        int index = length / 2;
        while (!(cumulative_fitness[index] < random_number) || 
               !(cumulative_fitness[index + 1] > random_number)) {
            if (cumulative_fitness[index] > random_number) {
                index /= 2;
            } else {
                index *= 2;
            }
        }
        return index;
    }

}
