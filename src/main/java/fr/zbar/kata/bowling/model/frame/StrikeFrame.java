package fr.zbar.kata.bowling.model.frame;

import fr.zbar.kata.bowling.model.Score;

public class StrikeFrame extends Frame {

    public StrikeFrame() {
        super(10, null);
    }

    @Override
    public Score score() {
        return Score.MAX;
    }
}
