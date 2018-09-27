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
        Random r = new Random();

        double[] cumulative_fitness = new double[length];
        cumulative_fitness[0] = populationList.get(0).getFitness();
        for (int i = 1; i < length; i++) {
            cumulative_fitness[i] = cumulative_fitness[i - 1] + populationList.get(i).getFitness();
        }
        
        return weighted_random_selection(length, cumulative_fitness, r, populationList);
    }

}
