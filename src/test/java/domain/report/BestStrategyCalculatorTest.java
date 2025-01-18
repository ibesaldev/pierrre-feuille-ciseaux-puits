package domain.report;

import domain.Symbol;
import domain.loop.EndedGameLoop;
import domain.loop.Strategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BestStrategyCalculatorTest {

    @Test
    void shouldReturnBestStrategyBetweenTwoStrategies() {
        BestStrategyCalculator calculator = new BestStrategyCalculator();
        Strategy strat1 = new Strategy(List.of(Symbol.ROCK));
        Strategy strat2 = new Strategy(List.of(Symbol.PAPER));
        EndedGameLoop endedGameLoop = new EndedGameLoop(0, 100, 0, strat1, strat2);
        assertThat(calculator.addLoop(endedGameLoop).bestStrategy()).containsExactly(strat2);
    }

    @Test
    void shouldReturnBestStrategyBetweenThreeStrategies() {
        BestStrategyCalculator calculator = new BestStrategyCalculator();
        Strategy strat1 = new Strategy(List.of(Symbol.ROCK));
        Strategy strat2 = new Strategy(List.of(Symbol.PAPER));
        Strategy strat3 = new Strategy(List.of(Symbol.ROCK, Symbol.PAPER));

        calculator.addLoop(new EndedGameLoop(50, 25, 25, strat3, strat2))
                .addLoop(new EndedGameLoop(25, 50, 25, strat1, strat3))
                .addLoop(new EndedGameLoop(0, 100, 0, strat1, strat2));

        assertThat(calculator.bestStrategy()).containsExactly(strat3);
    }

    @Test
    void shouldUseTieToDecideWhichStrategieIsTheBest() {
        BestStrategyCalculator calculator = new BestStrategyCalculator();
        Strategy strat1 = new Strategy(List.of(Symbol.ROCK));
        Strategy strat2 = new Strategy(List.of(Symbol.PAPER, Symbol.SCISSORS));
        Strategy strat3 = new Strategy(List.of(Symbol.ROCK, Symbol.PAPER));

        calculator.addLoop(new EndedGameLoop(50, 50, 0, strat1, strat2)) //tie
                .addLoop(new EndedGameLoop(0, 50, 50, strat1, strat3)) //strat3 wins
                .addLoop(new EndedGameLoop(50, 25, 25, strat2, strat3)); //strat2 wins

        assertThat(calculator.bestStrategy()).containsExactly(strat2);
    }


    @Test
    void shouldReturnMultipleBestStrategies() {
        BestStrategyCalculator calculator = new BestStrategyCalculator();
        Strategy strat1 = new Strategy(List.of(Symbol.PAPER, Symbol.SCISSORS));
        Strategy strat2 = new Strategy(List.of(Symbol.ROCK, Symbol.PAPER));
        Strategy strat3 = new Strategy(List.of(Symbol.ROCK, Symbol.SCISSORS));

        calculator.addLoop(new EndedGameLoop(50, 25, 25, strat1, strat2))
                .addLoop(new EndedGameLoop(25, 50, 25, strat1, strat3))
                .addLoop(new EndedGameLoop(50, 25, 25, strat2, strat3));

        assertThat(calculator.bestStrategy()).containsExactlyInAnyOrder(strat1, strat2, strat3);

    }
}