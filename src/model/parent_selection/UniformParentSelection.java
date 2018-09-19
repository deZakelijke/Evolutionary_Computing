package model.parent_selection;

import model.Individual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Collections;
import java.util.Random;

public class UniformParentSelection extends EmptyParentSelection implements ParentSelection {


    /**
     * init / constructor
     *this.nr_couples = nr_couples;
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

        for (int i = length - 1; i >= length - getNr_parents(); --i) {
            Collections.swap(populationList, i, r.nextInt(i + 1));
        }



        //todo: per couple een Individual[] maken van twee ouders zodat we weten welke bij elkaar horen

        throw new NotImplementedException();

//        return populationList.subList(length - getNr_parents(), length);

        // Bereken totale fitness score, prob. voor selectie is f/Sf
        // Zorgt mestal voor te snelle convergence
        // Windowing toepassen: min(f) van elke f afhalen
        //
    }

}
