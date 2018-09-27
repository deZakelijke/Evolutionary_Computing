package model.parent_selection;

import model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TournamentParentSelection extends EmptyParentSelection implements ParentSelection {


    /**
     * init / constructor
     *
     * @param nr_parents
     * @param nr_couples
     */
    public TournamentParentSelection(int nr_parents, int nr_couples) {
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

        return parents;
    }

}
