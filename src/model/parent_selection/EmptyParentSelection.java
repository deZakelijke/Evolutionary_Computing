package model.parent_selection;

import model.Individual;
import java.util.ArrayList;
import java.util.List;

public class EmptyParentSelection implements ParentSelection {

    private int nr_parents;

    private int nr_couples;

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
}
