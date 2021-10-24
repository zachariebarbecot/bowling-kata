package fr.zbar.kata.bowling;

import fr.zbar.kata.bowling.model.Score;
import fr.zbar.kata.bowling.model.frame.BonusFrame;
import fr.zbar.kata.bowling.model.frame.Frame;
import fr.zbar.kata.bowling.model.frame.NormalFrame;
import fr.zbar.kata.bowling.model.frame.SpareFrame;
import fr.zbar.kata.bowling.model.frame.StrikeFrame;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum ScoreRules {
    NONE(isFrameTypeOf(NormalFrame.class), frameScore()),
    SPARE(isFrameTypeOf(SpareFrame.class), frameScoreWithNextThrow()),
    STRIKE(isFrameTypeOf(StrikeFrame.class), frameScoreWithTwoNextThrow());

    private final BiPredicate<Integer, List<Frame>> is;
    private final BiFunction<Integer, List<Frame>, Score> score;

    ScoreRules(BiPredicate<Integer, List<Frame>> is, BiFunction<Integer, List<Frame>, Score> score) {
        this.is = is;
        this.score = score;
    }

    public Boolean typeOf(Integer i, List<Frame> frames) {
        return is.test(i, frames);
    }

    public Score scoreOfFrame(Integer i, List<Frame> frames) {
        return score.apply(i, frames);
    }

    private static <T> BiPredicate<Integer, List<Frame>> isFrameTypeOf(Class<T> type) {
        return (index, frames) -> type.isInstance(frames.get(index));
    }

    private static BiFunction<Integer, List<Frame>, Score> frameScore() {
        return (index, frames) -> frames.get(index).score();
    }

    private static BiFunction<Integer, List<Frame>, Score> frameScoreWithNextThrow() {
        return (index, frames) -> {
            Score firstNextThrowScore = firstNextThrowScore(index, frames);
            return frameScore().apply(index, frames).add(firstNextThrowScore);
        };
    }

    private static BiFunction<Integer, List<Frame>, Score> frameScoreWithTwoNextThrow() {
        return (index, frames) -> {
            Score secondNextThrowScore = secondNextThrowScore(index, frames);
            return frameScoreWithNextThrow().apply(index, frames).add(secondNextThrowScore);
        };
    }

    private static Score firstNextThrowScore(Integer index, List<Frame> frames) {
        return new Score(frames.get(index + 1).firstThrow());
    }

    private static Score secondNextThrowScore(Integer index, List<Frame> frames) {
        return isFrameTypeOf(StrikeFrame.class).test(index + 1, frames) ?
                new Score(frames.get(index + 2).firstThrow()) :
                bonusScore(index, frames);
    }

    private static Score bonusScore(Integer index, List<Frame> frames) {
        return isFrameTypeOf(BonusFrame.class).test(index + 1, frames) ?
                new Score(frames.get(index + 1).firstThrow()) :
                new Score(frames.get(index + 1).secondThrow());
    }
}
