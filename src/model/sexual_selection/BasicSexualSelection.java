package model.sexual_selection;

import model.Individual;

import java.util.List;
import java.util.Collections;
import java.util.Random;

public class BasicSexualSelection implements SexualSelectionInterface {

    private int nr_parents;
    private int nr_couples;

    /**
     * init / constructor
     *
     * @param nr_parents
     * @param nr_couples
     */
    public BasicSexualSelection(int nr_parents, int nr_couples) {
        this.nr_parents = nr_parents;
        this.nr_couples = nr_couples;
    }

    /**
     * Select parents uniform random, no weighting.
     */
    @Override
    public List<Individual> select(List<Individual> populationList) {
        int length = populationList.size();
        Random r = new Random();

        if (length < this.nr_couples) return null;

        for (int i = length - 1; i >= length - this.nr_couples; --i) {
            Collections.swap(populationList, i, r.nextInt(i + 1));
        }

        return populationList.subList(length - this.nr_couples, length);

        // Bereken totale fitness score, prob. voor selectie is f/Sf
        // Zorgt mestal voor te snelle convergence
        // Windowing toepassen: min(f) van elke f afhalen
        //
    }

}
