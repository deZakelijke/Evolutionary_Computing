package model.terminator;

import model.Population;

public class EmptyTerminator implements Terminator {
    @Override
    public boolean isItDone(Population population) {
        return false;
    }
}
