package model.parent_selection;

import model.Individual;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Random;

public class EmptyParentSelection implements ParentSelection {

    protected int nr_parents;

    protected int nr_couples;

    public EmptyParentSelection(int nr_parents, int nr_couples) {
        this.nr_parents = nr_parents;
        this.nr_couples = nr_couples;
    }

    @Override
    public List<Individual[]> select(List<Individual> populationList) {
        List<Individual[]> empty = new ArrayList<>();
        return empty;
    }

    public int getNr_parents() {
        return nr_parents;
    }

    public void setNr_parents(int nr_parents) {
        this.nr_parents = nr_parents;
    }

    public int getNr_couples() {
        return nr_couples;
    }

    public void setNr_couples(int nr_couples) {
        this.nr_couples = nr_couples;
    }

    protected int get_random_index(double[] cumulative_fitness, Random r, int length) {
        double random_number = r.nextDouble() * cumulative_fitness[length - 1];
        int index = 0;
        while (!(cumulative_fitness[index] < random_number) || 
               !(cumulative_fitness[index + 1] > random_number)) {
            index++;
        }
        return index;
    }

    protected List<Individual[]> weighted_random_selection(int length, double[] cumulative_fitness, 
                                                         Random r, List<Individual> populationList) {
        List<Individual[]> parents = new ArrayList<Individual[]>();
        HashSet<Integer> selected = new HashSet<Integer>();
        int random_index;

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

}
