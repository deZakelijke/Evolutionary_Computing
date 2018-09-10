package model.terminator;

public class EmptyTerminator implements Terminator {
    @Override
    public boolean isItDone() {
        return false;
    }
}
