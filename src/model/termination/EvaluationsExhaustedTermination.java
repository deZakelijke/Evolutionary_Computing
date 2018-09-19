package model.termination;

import model.Population;
/**
 *  runs untill evaluations are out
 */
public class EvaluationsExhaustedTermination extends EmptyTermination implements TerminationContext {

    private int maxEvaluations;

    public EvaluationsExhaustedTermination(int evaluations_limit_) {
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
        return "EvaluationsExhaustedTermination{" +
                "maxEvaluations=" + maxEvaluations +
                ", doneEvaluations=" + getDoneEvaluations() +
                '}';
    }
}
