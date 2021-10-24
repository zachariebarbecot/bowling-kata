package fr.zbar.kata.bowling;

import fr.zbar.kata.bowling.ext.GameParser;
import fr.zbar.kata.bowling.model.Score;
import fr.zbar.kata.bowling.model.frame.Frame;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public final class BowlingScoringSystem {

    private static final Integer FIRST_FRAME = 0;
    private final GameParser parser;

    public BowlingScoringSystem(GameParser parser) {
        this.parser = Objects.requireNonNull(parser);
    }

    public Score scoreFor(String gameForm) {
        List<Frame> frames = parser.frames(gameForm);
        return IntStream.range(FIRST_FRAME, frames.size())
                .mapToObj(index -> this.calculateScoreForEachFrame(index, frames))
                .flatMap(Optional::stream)
                .reduce(Score.EMPTY, Score::add);
    }

    private Optional<Score> calculateScoreForEachFrame(Integer i, List<Frame> frames) {
        return Arrays.stream(ScoreRules.values())
                .filter(rule -> rule.typeOf(i, frames))
                .map(rule -> rule.scoreOfFrame(i, frames))
                .findFirst();
    }
}
