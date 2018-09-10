package model.sexual_selection;


import model.Individual;

import java.util.List;

public interface SexualSelectionInterface {


    List<Individual> select(List<Individual> populationList);
}