package fr.zbar.kata.bowling.model.frame;

import fr.zbar.kata.bowling.model.Score;

public class NormalFrame extends Frame {

    public NormalFrame(Integer firstThrow, Integer secondThrow) {
        super(firstThrow, secondThrow);
    }

    public Score score() {
        return new Score(firstThrow + secondThrow);
    }
}
