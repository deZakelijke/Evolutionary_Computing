package model.parent_selection;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class UniformParentSelection extends EmptyParentSelection implements ParentSelection {


    /**
     * init / constructor
     *
     * @param nr_parents
     * @param nr_couples
     */
    public UniformParentSelection(int nr_parents, int nr_couples) {
        super(nr_parents, nr_couples);
    }

    /**
     * Select parents uniform random, no weighting.
     */
    @Override
    public List<Individual[]> select(List<Individual> populationList) {

        int length = populationList.size();
        Random r = new Random();
        if (length < getNr_parents()) return null;

        List<Individual[]> parents = new ArrayList<>();
        for (int i = 0 ; i < getNr_couples(); i++) {

            Individual[] new_couple = new Individual[getNr_parents()];
            for (int j = 0; j < getNr_parents(); j++) {
                new_couple[j] = populationList.get(r.nextInt(length));
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
