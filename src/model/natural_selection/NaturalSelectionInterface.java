package model.natural_selection;


import model.Individual;

import java.util.List;

public interface NaturalSelectionInterface {


    void kill(List<Individual> populationList);
}