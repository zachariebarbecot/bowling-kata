package fr.zbar.kata.bowling.model.frame;

import fr.zbar.kata.bowling.model.Score;

public class BonusFrame extends Frame {

    public BonusFrame(int lastThrow) {
        super(lastThrow, null);
    }

    @Override
    public Score score() {
        return new Score(firstThrow);
    }
}
