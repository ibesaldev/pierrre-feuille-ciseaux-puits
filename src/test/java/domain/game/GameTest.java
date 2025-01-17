package domain.game;

import domain.Symbol;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    
    @Test
    void rockShouldWinAgainstScissors() {
        Player player1 = new Player(Symbol.ROCK);
        Player player2 = new Player(Symbol.SCISSORS);

        assertWinner(new Game(player1, player2).play()).isEqualTo(player1);
        assertWinner(new Game(player2, player1).play()).isEqualTo(player1);
    }

    @Test
    void paperShouldWinAgainstRock() {
        Player player1 = new Player(Symbol.PAPER);
        Player player2 = new Player(Symbol.ROCK);

        assertWinner(new Game(player1, player2).play()).isEqualTo(player1);
        assertWinner(new Game(player2, player1).play()).isEqualTo(player1);
    }

    @Test
    void paperShouldWinAgainstWell() {
        Player player1 = new Player(Symbol.PAPER);
        Player player2 = new Player(Symbol.WELL);

        assertWinner(new Game(player1, player2).play()).isEqualTo(player1);
        assertWinner(new Game(player2, player1).play()).isEqualTo(player1);
    }

    @Test
    void scissorsShouldWinAgainstPaper() {
        Player player1 = new Player(Symbol.SCISSORS);
        Player player2 = new Player(Symbol.PAPER);

        assertWinner(new Game(player1, player2).play()).isEqualTo(player1);
        assertWinner(new Game(player2, player1).play()).isEqualTo(player1);
    }

    @Test
    void wellShouldWinAgainstRock() {
        Player player1 = new Player(Symbol.WELL);
        Player player2 = new Player(Symbol.ROCK);

        assertWinner(new Game(player1, player2).play()).isEqualTo(player1);
        assertWinner(new Game(player2, player1).play()).isEqualTo(player1);
    }

    @Test
    void wellShouldWinAgainstScissors() {
        Player player1 = new Player(Symbol.WELL);
        Player player2 = new Player(Symbol.SCISSORS);

        assertWinner(new Game(player1, player2).play()).isEqualTo(player1);
        assertWinner(new Game(player2, player1).play()).isEqualTo(player1);
    }

    @ParameterizedTest
    @EnumSource(Symbol.class)
    void sameSymbolShouldReturnNull(Symbol symbol) {
        Player player1 = new Player(symbol);
        Player player2 = new Player(symbol);

        assertIsTie(new Game(player1, player2).play());
        assertIsTie(new Game(player2, player1).play());
    }

    private void assertIsTie(Result result) {
        assertThat(result.isTie()).isTrue();
        assertThat(result.winner()).isNull();
    }

    private ObjectAssert<Player> assertWinner(Result result) {
        assertThat(result.isTie()).isFalse();
        return assertThat(result.winner());
    }
}
