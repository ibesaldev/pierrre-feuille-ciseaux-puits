package domain;

import domain.loop.EndedGameLoop;
import domain.loop.GameLoop;
import domain.loop.Strategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.utils.Assert.assertIsTie;
import static domain.utils.Assert.assertWinner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class GameLoopTest {

    @ParameterizedTest
    @EnumSource(Symbol.class)
    void sameSymbolShouldReturnTie(Symbol symbol) {
        Strategy strategy1 = new Strategy(List.of(symbol));
        Strategy strategy2 = new Strategy(List.of(symbol));

        assertIsTie(new GameLoop(strategy1, strategy2).run().result());
        assertIsTie(new GameLoop(strategy2, strategy1).run().result());
    }

    @ParameterizedTest
    @MethodSource("onlyOneSymbolStrategies")
    void shouldReturnWinner(Symbol symbol1, Symbol symbol2) {
        Strategy strategy1 = new Strategy(List.of(symbol1));
        Strategy strategy2 = new Strategy(List.of(symbol2));

        assertWinner(new GameLoop(strategy1, strategy2).run().result()).isEqualTo(strategy1);
        assertWinner(new GameLoop(strategy2, strategy1).run().result()).isEqualTo(strategy1);
    }

    static Stream<Arguments> onlyOneSymbolStrategies() {
        return Stream.of(
                Arguments.of(Symbol.ROCK, Symbol.SCISSORS),
                Arguments.of(Symbol.SCISSORS, Symbol.PAPER),
                Arguments.of(Symbol.PAPER, Symbol.ROCK),
                Arguments.of(Symbol.PAPER, Symbol.WELL),
                Arguments.of(Symbol.WELL, Symbol.ROCK),
                Arguments.of(Symbol.WELL, Symbol.SCISSORS)
        );
    }

    @Test
    void shouldIncreaseNbOfWins1WhenRunMultipleGames() {
        Strategy strategy1 = new Strategy(List.of(Symbol.ROCK));
        Strategy strategy2 = new Strategy(List.of(Symbol.SCISSORS));

        int nbOfGames = 10;
        EndedGameLoop run = new GameLoop(strategy1, strategy2).run(nbOfGames);

        assertWinner(run.result()).isEqualTo(strategy1);
        assertThat(run.nbOfWins1()).isEqualTo(nbOfGames);
        assertThat(run.nbOfWins2()).isEqualTo(0);
        assertThat(run.nbOfTies()).isEqualTo(0);
    }

    @Test
    void shouldIncreaseNbOfWins2WhenRunMultipleGames() {
        Strategy strategy1 = new Strategy(List.of(Symbol.SCISSORS));
        Strategy strategy2 = new Strategy(List.of(Symbol.ROCK));

        int nbOfGames = 10;
        EndedGameLoop run = new GameLoop(strategy1, strategy2).run(nbOfGames);

        assertWinner(run.result()).isEqualTo(strategy2);
        assertThat(run.nbOfWins2()).isEqualTo(nbOfGames);
        assertThat(run.nbOfWins1()).isEqualTo(0);
        assertThat(run.nbOfTies()).isEqualTo(0);
    }

    @Test
    void shouldIncreaseNbOfTiesWhenRunMultipleGames() {
        Strategy strategy1 = new Strategy(List.of(Symbol.SCISSORS));
        Strategy strategy2 = new Strategy(List.of(Symbol.SCISSORS));

        int nbOfGames = 10;
        EndedGameLoop run = new GameLoop(strategy1, strategy2).run(nbOfGames);

        assertIsTie(run.result());
        assertThat(run.nbOfTies()).isEqualTo(nbOfGames);
        assertThat(run.nbOfWins1()).isEqualTo(0);
        assertThat(run.nbOfWins2()).isEqualTo(0);
    }

    @Test
    void shouldSelectRandomSymbolInStrategy1() {
        Strategy strategy1 = new Strategy(List.of(Symbol.SCISSORS, Symbol.PAPER, Symbol.ROCK));
        Strategy strategy2 = new Strategy(List.of(Symbol.SCISSORS));

        int nbOfGames = 10000;
        EndedGameLoop run = new GameLoop(strategy1, strategy2).run(nbOfGames);

        assertIsTie(run.result());
        assertThat(run.nbOfTies()).isCloseTo(3333, within(100));
        assertThat(run.nbOfWins1()).isCloseTo(3333, within(100));
        assertThat(run.nbOfWins2()).isCloseTo(3333, within(100));
    }

    @Test
    void shouldSelectRandomSymbolInStrategy2() {
        Strategy strategy1 = new Strategy(List.of(Symbol.SCISSORS));
        Strategy strategy2 = new Strategy(List.of(Symbol.SCISSORS, Symbol.PAPER, Symbol.ROCK));

        int nbOfGames = 10000;
        EndedGameLoop run = new GameLoop(strategy1, strategy2).run(nbOfGames);

        assertIsTie(run.result());
        assertThat(run.nbOfTies()).isCloseTo(3333, within(100));
        assertThat(run.nbOfWins1()).isCloseTo(3333, within(100));
        assertThat(run.nbOfWins2()).isCloseTo(3333, within(100));
    }

}