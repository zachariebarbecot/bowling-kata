package fr.zbar.kata.bowling.model.frame;

import fr.zbar.kata.bowling.model.Score;

public abstract class Frame {

    protected final Integer firstThrow;
    protected final Integer secondThrow;

    protected Frame(Integer firstThrow, Integer secondThrow) {
        this.firstThrow = firstThrow;
        this.secondThrow = secondThrow;
    }

    public abstract Score score();

    public Integer firstThrow() {
        return firstThrow;
    }

    public Integer secondThrow() {
        return secondThrow;
    }
}
