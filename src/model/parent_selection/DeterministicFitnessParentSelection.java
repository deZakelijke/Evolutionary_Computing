package model.parent_selection;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Comparator;

public class DeterministicFitnessParentSelection extends EmptyParentSelection implements ParentSelection {


    /**
     * init / constructor
     *
     * @param nr_parents
     * @param nr_couples
     */
    public DeterministicFitnessParentSelection(int nr_parents, int nr_couples) {
        super(nr_parents, nr_couples);
    }

    /**
     * Select parents deterministically, based on their fitness.
     * Each parent will be selecten fitness/total_parents_required times
     * and the total parents that are required is nr_parents * nr_couples
     * A couple will be assigned parents in descending fitness, starting with
     * the parent with the highest fitness that may still be selected.
     *
     * If there were selected insufficient parents, the parents with the
     * highes selection will be selected again.
     */
    @Override
    public List<Individual[]> select(List<Individual> populationList) {
        int length = populationList.size();
        if (length < this.nr_parents) return null;
        List<Individual[]> parents = new ArrayList<Individual[]>();

        double total_fitness = populationList.stream().mapToDouble(Individual -> Individual.getFitness()).sum();
        double ratio = total_fitness / (double) this.nr_parents / (double) this.nr_couples;
        populationList.sort(Comparator.comparing(Individual::getFitness));
        HashMap<Integer, Double> selection_counter = new HashMap<Integer, Double>();
        int remaining_counter = 0;

        // Keep track of how may times parents can still be selected
        for (int i = 0; i < length; i++) { 
            double counter = populationList.get(i).getFitness() / ratio;
            selection_counter.put(i, counter);
            remaining_counter += (int) counter;
        }


        for (int i = 0 ; i < this.nr_couples; i++) {
            Individual[] new_couple = new Individual[this.nr_parents];
            for (int j = 0, k; j < this.nr_parents; j++) {
                for (k = j; selection_counter.get(k) <= 0; k++);
                new_couple[j] = populationList.get(k);
                selection_counter.put(k, selection_counter.get(k) - 1);
            }
            parents.add(new_couple);
        }
        return parents;
        // Bereken totale fitness score, prob. voor selectie is f/Sf
        // Zorgt mestal voor te snelle convergence
        // Windowing toepassen: min(f) van elke f afhalen
        //
    }

}
