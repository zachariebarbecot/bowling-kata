package units;

import fr.zbar.kata.bowling.BowlingScoringSystem;
import fr.zbar.kata.bowling.ext.GameParser;
import fr.zbar.kata.bowling.model.Score;
import fr.zbar.kata.bowling.model.frame.BonusFrame;
import fr.zbar.kata.bowling.model.frame.Frame;
import fr.zbar.kata.bowling.model.frame.NormalFrame;
import fr.zbar.kata.bowling.model.frame.SpareFrame;
import fr.zbar.kata.bowling.model.frame.StrikeFrame;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {

    @Test
    void should_score_zero_when_game_is_full_of_zero() {
        GameParser parserWithGutterFrames = (String game) -> gutterFrames();
        BowlingScoringSystem bowlingScoringSystem = new BowlingScoringSystem(parserWithGutterFrames);
        Score expectedScore = Score.EMPTY;

        Score result = bowlingScoringSystem.scoreFor("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");

        assertThat(result).isEqualTo(expectedScore);
    }

    @Test
    void should_score_sixty_when_game_is_full_of_three() {
        GameParser parserWithGutterFrames = (String game) -> threeFrames();
        BowlingScoringSystem bowlingScoringSystem = new BowlingScoringSystem(parserWithGutterFrames);
        Score expectedScore = new Score(60);

        Score result = bowlingScoringSystem.scoreFor("3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3");

        assertThat(result).isEqualTo(expectedScore);
    }

    @Test
    void should_score_sixty_eight_when_game_is_random_with_one_spare() {
        GameParser parserWithOneSpareFrames = (String game) -> randomFramesWithOneSpare();
        BowlingScoringSystem bowlingScoringSystem = new BowlingScoringSystem(parserWithOneSpareFrames);
        Score expectedScore = new Score(68);

        Score result = bowlingScoringSystem.scoreFor("3 4 2 3 0 5 6 / 2 3 7 1 5 2 4 2 9 0 3 1");

        assertThat(result).isEqualTo(expectedScore);
    }

    @Test
    void should_score_seventy_one_when_game_is_random_with_one_strike() {
        GameParser parserWithOneSpareFrames = (String game) -> randomFramesWithOneStrike();
        BowlingScoringSystem bowlingScoringSystem = new BowlingScoringSystem(parserWithOneSpareFrames);
        Score expectedScore = new Score(71);

        Score result = bowlingScoringSystem.scoreFor("3 4 2 3 0 5 X 2 3 7 1 5 2 4 2 9 0 3 1");

        assertThat(result).isEqualTo(expectedScore);
    }

    @Test
    void should_score_ninety_six_when_game_is_random_with_two_consecutive_strike() {
        GameParser parserWithOneSpareFrames = (String game) -> randomFramesWithTwoConsecutiveStrike();
        BowlingScoringSystem bowlingScoringSystem = new BowlingScoringSystem(parserWithOneSpareFrames);
        Score expectedScore = new Score(96);

        Score result = bowlingScoringSystem.scoreFor("3 4 2 3 0 5 X X 7 1 5 2 4 2 9 0 3 1");

        assertThat(result).isEqualTo(expectedScore);
    }

    @Test
    void should_score_max_score_when_game_is_full_of_strike() {
        GameParser parserWithOneSpareFrames = (String game) -> fullStrikeFrame();
        BowlingScoringSystem bowlingScoringSystem = new BowlingScoringSystem(parserWithOneSpareFrames);
        Score expectedScore = new Score(300);

        Score result = bowlingScoringSystem.scoreFor("X X X X X X X X X X X X");

        assertThat(result).isEqualTo(expectedScore);
    }

    @Test
    void should_score_one_hundred_ninety_one_when_game_is_full_of_spare_and_finish_with_strike() {
        GameParser parserWithOneSpareFrames = (String game) -> fullSpareFrameWithLastStrike();
        BowlingScoringSystem bowlingScoringSystem = new BowlingScoringSystem(parserWithOneSpareFrames);
        Score expectedScore = new Score(191);

        Score result = bowlingScoringSystem.scoreFor("X X X X X X X X X X X X");

        assertThat(result).isEqualTo(expectedScore);
    }

    private List<Frame> gutterFrames() {
        return List.of(
                new NormalFrame(0, 0),
                new NormalFrame(0, 0),
                new NormalFrame(0, 0),
                new NormalFrame(0, 0),
                new NormalFrame(0, 0),
                new NormalFrame(0, 0),
                new NormalFrame(0, 0),
                new NormalFrame(0, 0),
                new NormalFrame(0, 0),
                new NormalFrame(0, 0)
        );
    }

    private List<Frame> threeFrames() {
        return List.of(
                new NormalFrame(3, 3),
                new NormalFrame(3, 3),
                new NormalFrame(3, 3),
                new NormalFrame(3, 3),
                new NormalFrame(3, 3),
                new NormalFrame(3, 3),
                new NormalFrame(3, 3),
                new NormalFrame(3, 3),
                new NormalFrame(3, 3),
                new NormalFrame(3, 3)
        );
    }

    private List<Frame> randomFramesWithOneSpare() {
        return List.of(
                new NormalFrame(3, 4),
                new NormalFrame(2, 3),
                new NormalFrame(0, 5),
                new SpareFrame(6, 4),
                new NormalFrame(2, 3),
                new NormalFrame(7, 1),
                new NormalFrame(5, 2),
                new NormalFrame(4, 2),
                new NormalFrame(9, 0),
                new NormalFrame(3, 1)
        );
    }

    private List<Frame> randomFramesWithOneStrike() {
        return List.of(
                new NormalFrame(3, 4),
                new NormalFrame(2, 3),
                new NormalFrame(0, 5),
                new StrikeFrame(),
                new NormalFrame(2, 3),
                new NormalFrame(7, 1),
                new NormalFrame(5, 2),
                new NormalFrame(4, 2),
                new NormalFrame(9, 0),
                new NormalFrame(3, 1)
        );
    }

    private List<Frame> randomFramesWithTwoConsecutiveStrike() {
        return List.of(
                new NormalFrame(3, 4),
                new NormalFrame(2, 3),
                new NormalFrame(0, 5),
                new StrikeFrame(),
                new StrikeFrame(),
                new NormalFrame(7, 1),
                new NormalFrame(5, 2),
                new NormalFrame(4, 2),
                new NormalFrame(9, 0),
                new NormalFrame(3, 1)
        );
    }

    private List<Frame> fullStrikeFrame() {
        return List.of(
                new StrikeFrame(),
                new StrikeFrame(),
                new StrikeFrame(),
                new StrikeFrame(),
                new StrikeFrame(),
                new StrikeFrame(),
                new StrikeFrame(),
                new StrikeFrame(),
                new StrikeFrame(),
                new StrikeFrame(),
                new BonusFrame(10)
        );
    }

    private List<Frame> fullSpareFrameWithLastStrike() {
        return List.of(
                new SpareFrame(9, 1),
                new SpareFrame(9, 1),
                new SpareFrame(9, 1),
                new SpareFrame(9, 1),
                new SpareFrame(9, 1),
                new SpareFrame(9, 1),
                new SpareFrame(9, 1),
                new SpareFrame(9, 1),
                new SpareFrame(9, 1),
                new SpareFrame(9, 1),
                new BonusFrame(10)
        );
    }
}
