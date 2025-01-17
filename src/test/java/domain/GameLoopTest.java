package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.utils.Assert.assertWinner;

class GameLoopTest {

    @ParameterizedTest
    @MethodSource("onlyOneSymbolStrategies")
    void shouldReturnWinner(Symbol symbol1, Symbol symbol2) {
        Strategy strategy1 = new Strategy(List.of(symbol1));
        Strategy strategy2 = new Strategy(List.of(symbol2));

        assertWinner(new GameLoop(strategy1, strategy2).run()).isEqualTo(strategy1);
        assertWinner(new GameLoop(strategy2, strategy1).run()).isEqualTo(strategy1);
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

}