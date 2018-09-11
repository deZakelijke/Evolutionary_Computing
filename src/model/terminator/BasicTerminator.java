package model.terminator;

import model.Population;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BasicTerminator implements Terminator {

    private int maxEvaluations;
    private int doneEvaluations = 0;

    public BasicTerminator(int evaluations_limit_) {
        setMaxEvaluations(evaluations_limit_);
    }

    private void setMaxEvaluations(int maxEvaluations) {
        this.maxEvaluations = maxEvaluations;
    }

    @Override
    public boolean isItDone(Population population) {
        return maxEvaluations<=doneEvaluations;
    }

    @Override
    public void addEvaluation() {
        doneEvaluations = 0;
    }

    public int getMaxEvaluations() {
        return maxEvaluations;
    }


    public int getDoneEvaluations() {
        return doneEvaluations;
    }

    @Override
    public String toString() {
        return "BasicTerminator{" +
                "maxEvaluations=" + maxEvaluations +
                ", doneEvaluations=" + doneEvaluations +
                '}';
    }
}
