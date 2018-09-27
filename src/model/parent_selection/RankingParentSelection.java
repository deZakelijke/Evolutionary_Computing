package model.parent_selection;

import model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

public class RankingParentSelection extends EmptyParentSelection implements ParentSelection {


    /**
     * init / constructor
     *
     * @param nr_parents
     * @param nr_couples
     */
    public RankingParentSelection(int nr_parents, int nr_couples) {
        super(nr_parents, nr_couples);
    }

    /**
     * Select parents stochastically by basing their weigth on their relative ranking
     */
    @Override
    public List<Individual[]> select(List<Individual> populationList) {
        int length = populationList.size();
        if (length < getNr_parents()) return null;
        Random r = new Random();

        double ranking_parameter = 1.5;
        double ranking_const_1 = (2 - ranking_parameter) / (double) length;
        double ranking_const_2 = 2 * (ranking_parameter - 1) / (double)(length * (length - 1));

        double[] cumulative_fitness = new double[length];
        populationList.sort(Comparator.comparing(Individual::getFitness));
        cumulative_fitness[0] = ranking_const_1;
        for (int i = 1; i < length; i++) {
            cumulative_fitness[i] = ranking_const_1 + ranking_const_2 * (double)i;
            cumulative_fitness[i] += cumulative_fitness[i - 1];
        }

        return weighted_random_selection(length, cumulative_fitness, r, populationList);
    }
}
