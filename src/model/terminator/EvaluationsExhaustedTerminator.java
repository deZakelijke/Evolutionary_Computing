package model.terminator;

import model.Population;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *  runs untill evaluations are out
 */
public class EvaluationsExhaustedTerminator extends EmptyTerminator implements Terminator {

    private int maxEvaluations;

    public EvaluationsExhaustedTerminator(int evaluations_limit_) {
        setMaxEvaluations(evaluations_limit_);
    }

    @Override
    public boolean isItDone(Population population) {
        return maxEvaluations <= getDoneEvaluations();
    }

    private void setMaxEvaluations(int maxEvaluations) {
        this.maxEvaluations = maxEvaluations;
    }

    @Override
    public String toString() {
        return "EvaluationsExhaustedTerminator{" +
                "maxEvaluations=" + maxEvaluations +
                ", doneEvaluations=" + getDoneEvaluations() +
                '}';
    }
}
