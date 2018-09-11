package model.terminator;

import model.Population;

public interface Terminator {

    boolean isItDone(Population population);

    void addEvaluation();

    public int getDoneEvaluations();
}

