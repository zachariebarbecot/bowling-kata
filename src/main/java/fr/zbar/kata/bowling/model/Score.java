package fr.zbar.kata.bowling.model;

import java.util.Objects;

public record Score(Integer value) {

    public static final Score EMPTY = new Score(0);
    public static final Score MAX = new Score(10);

    public Score {
        Objects.requireNonNull(value);
    }

    public Score add(Score score) {
        return new Score(value + score.value);
    }
}
