package model.recombination;

import model.Individual;
import java.util.ArrayList;
import java.util.List;

public class EmptyRecombination implements Recombination {



    @Override
    public List<Individual> reproduce(List<Individual[]> selectedForReproduction) {
        List<Individual> empty = new ArrayList<>();
        return empty;
    }
}
