package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.utils.Assert.assertIsTie;
import static domain.utils.Assert.assertWinner;

class EndedGameLoopTest {

    @Test
    void shouldReturnTieWhenTheGapBetweenWinRatesIsLessThan3() {
        Strategy emptyStrat = new Strategy(List.of());
        EndedGameLoop endedGameLoop = new EndedGameLoop(49, 51, 0, emptyStrat, emptyStrat);

        assertIsTie(endedGameLoop.result());
    }

    @Test
    void shouldReturnWinnerWhenTheGapBetweenWinRatesIsGreaterThan3() {
        Strategy strat1 = new Strategy(List.of());
        Strategy strat2 = new Strategy(List.of());
        EndedGameLoop endedGameLoop = new EndedGameLoop(48, 52, 0, strat1, strat2);

        assertWinner(endedGameLoop.result()).isSameAs(strat2);
    }

}