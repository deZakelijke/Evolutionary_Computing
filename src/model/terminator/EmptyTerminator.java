package model.terminator;

import model.Population;

public class EmptyTerminator implements Terminator {

    private int doneEvaluations;

    public EmptyTerminator() {
    }

    @Override
    public boolean isItDone(Population population) {
        return false;
    }

    @Override
    public void addEvaluation() {
        doneEvaluations+=1;
    }

    @Override
    public int getDoneEvaluations() {
        return doneEvaluations;
    }
}
