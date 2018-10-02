package model.exception;

public class EvaluationsExhaustedException extends Exception {

    private int done;

    public EvaluationsExhaustedException(int done, org.vu.contest.ContestEvaluation evaluator) {
        this.done = done;
        System.out.println(evaluator.getProperties().toString());
    }

    public EvaluationsExhaustedException(Exception e) {
        setStackTrace(e.getStackTrace());
    }


    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "EvaluationsExhaustedException{" +

                ", done=" + done +
                '}';
    }
}
