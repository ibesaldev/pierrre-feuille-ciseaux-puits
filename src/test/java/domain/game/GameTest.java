package domain.game;

import domain.Result;
import domain.Symbol;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @ParameterizedTest
    @EnumSource(Symbol.class)
    void sameSymbolShouldReturnTie(Symbol symbol) {
        Player player1 = new Player(symbol);
        Player player2 = new Player(symbol);

        assertIsTie(new Game(player1, player2).play());
        assertIsTie(new Game(player2, player1).play());
    }

    @ParameterizedTest
    @MethodSource("games")
    void shouldReturnWinner(Symbol symbol1, Symbol symbol2) {
        Player player1 = new Player(symbol1);
        Player player2 = new Player(symbol2);

        assertWinner(new Game(player1, player2).play()).isEqualTo(player1);
        assertWinner(new Game(player2, player1).play()).isEqualTo(player1);
    }

    static Stream<Arguments> games() {
        return Stream.of(
                Arguments.of(Symbol.ROCK, Symbol.SCISSORS),
                Arguments.of(Symbol.SCISSORS, Symbol.PAPER),
                Arguments.of(Symbol.PAPER, Symbol.ROCK),
                Arguments.of(Symbol.PAPER, Symbol.WELL),
                Arguments.of(Symbol.WELL, Symbol.ROCK),
                Arguments.of(Symbol.WELL, Symbol.SCISSORS)
        );
    }

    private void assertIsTie(Result<Player> result) {
        assertThat(result.isTie()).isTrue();
        assertThat(result.winner()).isNull();
    }

    private ObjectAssert<Player> assertWinner(Result<Player> result) {
        assertThat(result.isTie()).isFalse();
        return assertThat(result.winner());
    }
}
