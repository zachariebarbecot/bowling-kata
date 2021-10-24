package fr.zbar.kata.bowling.model.frame;

import fr.zbar.kata.bowling.model.Score;

public class SpareFrame extends Frame {

    public SpareFrame(Integer firstThrow, Integer secondThrow) {
        super(firstThrow, secondThrow);
    }

    @Override
    public Score score() {
        return Score.MAX;
    }
}
